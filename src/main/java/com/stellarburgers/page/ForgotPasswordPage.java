package com.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    //Локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement passwordButtonLogin;

    //Метод клика по кнопке "Войти"
    @Step("click login button")
    public void clickEnterButton() {
        passwordButtonLogin.click();
    }
}
