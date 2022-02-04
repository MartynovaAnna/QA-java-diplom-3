package com.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    //Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    public SelenideElement mainButtonLogin;

    //Локатор кнопки "Войти в личный кабинет"
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    public SelenideElement mainButtonProfile;

    //Локатор раздела "Булки"
    @FindBy(how = How.XPATH,using = ".//div[span[text()='Булки']]")
    public SelenideElement mainButtonBuns;

    //Локатор раздела "Соусы"
    @FindBy(how = How.XPATH,using = ".//div[span[text()='Соусы']]")
    public SelenideElement mainButtonSauces;

    //Локатор раздела "Начинки"
    @FindBy(how = How.XPATH,using = ".//div[span[text()='Начинки']]")
    public SelenideElement mainButtonFillings;

    //Локатор заголовка "Булки"
    @FindBy(how = How.XPATH,using = ".//h2[text()='Булки']")
    public SelenideElement mainHeaderBun;

    //Локатор заголовка "Соусы"
    @FindBy(how = How.XPATH,using = ".//h2[text()='Соусы']")
    public SelenideElement mainHeaderSauce;

    //Локатор заголовка "Начинки"
    @FindBy(how = How.XPATH,using = ".//h2[text()='Начинки']")
    public SelenideElement mainHeaderFilling;


    //Метод для клика на кнопку "Войти в аккаунт"
    @Step("Click login button")
    public void clickButtonLogin() {
        mainButtonLogin.click();
    }

    //Метод для клика на кнопку "Войти в личный кабинет"
    @Step("Click profile button")
    public void clickButtonProfile() {
        SelenideElement mainButtonProfile = $$(byClassName("AppHeader_header__linkText__3q_va")).get(2);
        mainButtonProfile.click(); }

    //Метод для клика по кнопке "Булки"
    @Step("Click buns button")
    public void clickBuns() {
        mainButtonBuns.click();
    }

    //Метод для клика по кнопке "Соусы"
    @Step("Click sauces button")
    public void clickSauces() {
        mainButtonSauces.click();
    }

    //Метод для клика по кнопке "Начинки"
    @Step("Click fillings button")
    public void clickFillings() {
        mainButtonFillings.click();
    }
}
