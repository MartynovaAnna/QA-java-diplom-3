package com.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    //Локатор кнопки "Забыли пароль"
    @FindBy(how = How.XPATH, using = "//*[@href = '/forgot-password']")
    public SelenideElement loginForgotPassword;
    //Локатор поля "Email"
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement loginFieldEmail;
    //Локатор поля "Пароль"
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement loginFieldPassword;
    //Локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButtonLogin;

    //Метод для получения текста кнопки "Войти"
    @Step("Get login button text")
    public String loginButtonGetText() {
        return loginButtonLogin.getText();
    }

    //Метод для заполнения поля "Email"
    @Step("Set email field")
    public void setEmail(String email) {
        loginFieldEmail.setValue(email);
    }

    //Метод для заполнения поля "Пароль"
    @Step("Set password field")
    public void setPassword(String password) {
        loginFieldPassword.setValue(password);
    }

    //Метод для клика на кнопку "Войти"
    @Step("Click login button")
    public void clickLogin() {
        loginButtonLogin.click();
    }

    //Метод для заполнения полей страницы входа
    @Step("Set login page fields")
    public void loginUser(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogin();
    }
}
