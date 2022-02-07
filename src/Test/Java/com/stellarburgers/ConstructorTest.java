package com.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.stellarburgers.page.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.endsWith;

public class ConstructorTest {

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
    @DisplayName("Testing buns section")
    @Description("This is the test for checking buns section in constructor")
    public void shouldGoToBunsSection() {
        MainPage page = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        page.clickSauces();
        sleep(300);
        page.clickBuns();

        String actualText = page.getBunsText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing sauces section")
    @Description("This is the test for checking sauces section in constructor")
    public void shouldGoToSaucesSection() {
        MainPage page = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        page.clickSauces();

        String actualText = page.getSaucesText();

        Assert.assertThat(actualText, endsWith("Соусы"));

    }

    @Test
    @DisplayName("Testing fillings section")
    @Description("This is the test for checking fillings section in constructor")
    public void shouldGoToFillingsSection() {
        MainPage page = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        page.clickFillings();

        String actualText = page.getFillingsText();

        Assert.assertThat(actualText, endsWith("Начинки"));

    }
}
