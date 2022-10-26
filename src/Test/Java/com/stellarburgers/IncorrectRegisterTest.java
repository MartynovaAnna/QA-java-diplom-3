package com.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.stellarburgers.page.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.endsWith;

public class IncorrectRegisterTest {

    public static final String EMAIL_POSTFIX = "@yandex.ru";
    String testName = RandomStringUtils.randomAlphabetic(10);
    String testEmail = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
    String incorrectPassword = RandomStringUtils.randomAlphabetic(5);

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        Configuration.startMaximized = true;
    }

    @After
    public void after() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Testing registration with incorrect data")
    @Description("This is the test for checking user registration with invalid data")
    public void shouldNotRegisterWithIncorrectData() {
        RegisterPage page = open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
        page.registerUser(testName, testEmail, incorrectPassword);
        String actualText = page.getRegisterErrorText();

        Assert.assertThat(actualText, endsWith("Некорректный пароль"));
    }
}
