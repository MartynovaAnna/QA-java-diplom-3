package com.stellarburgers;

import com.Base;
import com.codeborne.selenide.Configuration;
import com.model.Tokens;
import com.model.UserRegisterResponse;
import com.stellarburgers.page.LoginPage;
import com.stellarburgers.page.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;

public class RegisterChromeTest {

    public static final String EMAIL_POSTFIX = "@yandex.ru";
    String testName = RandomStringUtils.randomAlphabetic(10);
    String testEmail = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
    String testPassword = RandomStringUtils.randomAlphabetic(10);

    @Before
    public void driver() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        Configuration.startMaximized = true;
    }

    @After
    public void delete() {
        if (Tokens.getAccessToken() == null) {
            return;
        }
        given()
                .spec(Base.getBaseSpec())
                .auth().oauth2(Tokens.getAccessToken())
                .when()
                .delete("auth/user")
                .then()
                .statusCode(202);
    }

    @After
    public void after() {
        closeWebDriver();
    }


    @Test
    @DisplayName("Testing registration with correct data")
    @Description("This is the test for checking user registration with valid data")
    public void shouldRegisterWithCorrectData() {
        RegisterPage page = open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
        page.registerUser(testName, testEmail, testPassword);

        LoginPage loginPage = page(LoginPage.class);
        $(loginPage.loginForgotPassword).shouldBe(visible);

        String actualText = loginPage.loginButtonGetText();
        Assert.assertThat(actualText, endsWith("Войти"));

        login();
    }

    @Step("Login and get access token")
    public Map<String, String> login() {
        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", testEmail);
        inputDataMap.put("password", testPassword);
        inputDataMap.put("name", testName);

        UserRegisterResponse response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post("auth/login")
                .body()
                .as(UserRegisterResponse.class);

        Map<String, String> responseData = new HashMap<>();
        if (response != null) {
            responseData.put("email", testEmail);
            responseData.put("name", testPassword);
            responseData.put("password", testPassword);

            Tokens.setAccessToken(response.getAccessToken().substring(7));
        }
        return responseData;
    }
}