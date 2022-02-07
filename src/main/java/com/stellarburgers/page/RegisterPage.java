package com.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$$;

public class RegisterPage {

    //Локатор поля "Имя"
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement registerFieldName;

    //Локатор поля "Email"
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement registerFieldEmail;

    //Локатор поля "Пароль"
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement registerFieldPassword;

    //Локатор уведомления "Некорректный пароль"
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement registerTextError;

    //Локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registerButtonRegister;

    //Локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement registerButtonLogin;

    //Метод для получения текста уведомления о неверном пароле
    @Step("Get register error text")
    public String getRegisterErrorText() {
        return registerTextError.getText();
    }

    //Метод для заполнения поля "Имя"
    @Step("Set name field")
    public void setName(String name) {
        registerFieldName.setValue(name);
    }

    //Метод для заполнения поля "Email"
    @Step("Set email field")
    public void setEmail(String email) {
        SelenideElement registerFieldEmail = $$(byName("name")).get(1);
        registerFieldEmail.setValue(email);
    }

    //Метод для заполнения поля "Пароль"
    @Step("Set password field")
    public void setPassword(String password) {
        registerFieldPassword.setValue(password);
    }

    //Метод для клика по кнопке "Зарегистрироваться"
    @Step("Click register button")
    public void clickRegister() {
        registerButtonRegister.click();
    }

    //Метод для заполнения полей страницы регистрации
    @Step("Set register page fields")
    public void registerUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegister();
    }

    //Метод для клика по кнопке "Войти"
    @Step("Click login button")
    public void clickEnterButton() {
        registerButtonLogin.click();
    }
}
