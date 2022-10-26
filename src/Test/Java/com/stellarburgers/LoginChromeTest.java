package com.stellarburgers;

import com.Base;
import com.codeborne.selenide.Configuration;
import com.model.Tokens;
import com.model.UserRegisterResponse;
import com.stellarburgers.page.ForgotPasswordPage;
import com.stellarburgers.page.LoginPage;
import com.stellarburgers.page.MainPage;
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

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;

public class LoginChromeTest {

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
    @DisplayName("Testing login from main page")
    @Description("This is the test for checking login button in the main page")
    public void shouldLoginFromMainPage() {

        registerNewUser();
        MainPage page = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        page.clickButtonLogin();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        String actualText = page.getBunsButtonText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing login from profile page")
    @Description("This is the test for checking login button in the profile page")
    public void shouldLoginFromProfileButton() {
        registerNewUser();
        MainPage page = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        page.clickButtonProfile();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        String actualText = page.getBunsButtonText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing login from registration page")
    @Description("This is the test for checking login button in the registration page")
    public void shouldLoginFromRegistrationForm() {
        registerNewUser();
        RegisterPage page = open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
        page.clickEnterButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        MainPage mainPage = page(MainPage.class);

        String actualText = mainPage.getBunsButtonText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing login from forgot password page")
    @Description("This is the test for checking login button in the forgot password page")
    public void shouldLoginFromForgotPasswordForm() {
        registerNewUser();
        ForgotPasswordPage page = open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPage.class);
        page.clickEnterButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        MainPage mainPage = page(MainPage.class);

        String actualText = mainPage.getBunsButtonText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Step("Create new user")
    public Map<String, String> registerNewUser() {
        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", testEmail);
        inputDataMap.put("password", testPassword);
        inputDataMap.put("name", testName);

        UserRegisterResponse response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post("auth/register")
                .body()
                .as(UserRegisterResponse.class);

        Map<String, String> responseData = new HashMap<>();
        if (response != null) {
            responseData.put("email", testEmail);
            responseData.put("name", testName);
            responseData.put("password", testPassword);

            Tokens.setAccessToken(response.getAccessToken().substring(7));
            Tokens.setRefreshToken(response.getRefreshToken());
        }
        return responseData;
    }
}
