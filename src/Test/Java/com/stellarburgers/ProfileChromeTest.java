package com.stellarburgers;

import com.Base;
import com.codeborne.selenide.Configuration;
import com.model.Tokens;
import com.model.UserRegisterResponse;
import com.stellarburgers.page.LoginPage;
import com.stellarburgers.page.MainPage;
import com.stellarburgers.page.ProfilePage;
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

public class ProfileChromeTest {

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
    @DisplayName("Testing profile page")
    @Description("This is the test for checking profile page for authorized user")
    public void shouldGoToProfile() {
        registerNewUser();
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonProfile();

        ProfilePage profilePage = page(ProfilePage.class);
        String actualText = profilePage.getProfileText();
        Assert.assertThat(actualText, endsWith("персональные данные"));

    }

    @Test
    @DisplayName("Testing constructor page from button")
    @Description("This is the test for checking going from profile page to constructor page")
    public void shouldGoToConstructorFromButton() {
        registerNewUser();
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonProfile();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickButtonConstructor();

        String actualText = mainPage.getBunsButtonText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing constructor page from logo")
    @Description("This is the test for checking going from logo to constructor page")
    public void shouldGoToConstructorFromLogo() {
        registerNewUser();
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonProfile();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLogo();

        String actualText = mainPage.getBunsButtonText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing exit from profile")
    @Description("This is the test for checking exit button on profile page")
    public void shouldExitFromProfile() {
        registerNewUser();
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginUser(testEmail, testPassword);

        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonProfile();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickExit();
        $(loginPage.loginForgotPassword).shouldBe(visible);

        String actualText = loginPage.loginButtonGetText();
        Assert.assertThat(actualText, endsWith("Войти"));

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
