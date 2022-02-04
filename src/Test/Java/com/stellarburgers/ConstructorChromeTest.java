package com.stellarburgers;

import com.stellarburgers.page.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.endsWith;

public class ConstructorChromeTest {

    @Before
    public void driver(){System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");}

    @After
    public void after(){closeWebDriver();}

    @Test
    @DisplayName("Testing buns section")
    @Description("This is the test for checking buns section in constructor")
    public void shouldGoToBunsSection() {
        MainPage page = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        page.clickSauces();
        sleep(300);
        page.clickBuns();

        String actualText = page.mainHeaderBun.getText();

        Assert.assertThat(actualText, endsWith("Булки"));

    }

    @Test
    @DisplayName("Testing sauces section")
    @Description("This is the test for checking sauces section in constructor")
    public void shouldGoToSaucesSection() {
        MainPage page = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        page.clickSauces();

        String actualText = page.mainHeaderSauce.getText();

        Assert.assertThat(actualText, endsWith("Соусы"));

    }

    @Test
    @DisplayName("Testing fillings section")
    @Description("This is the test for checking fillings section in constructor")
    public void shouldGoToFillingsSection() {
        MainPage page = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        page.clickFillings();

        String actualText = page.mainHeaderFilling.getText();

        Assert.assertThat(actualText, endsWith("Начинки"));

    }
}
