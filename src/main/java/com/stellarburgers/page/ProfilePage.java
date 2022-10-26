package com.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    //Локатор кнопки "Профиль"
    @FindBy(how = How.CLASS_NAME, using = "Account_link__2ETsJ")
    private SelenideElement profileButtonProfile;

    //Локатор кнопки "Конструктор"
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    private SelenideElement profileButtonConstructor;

    //Локатор логотипа Stellar Burgers
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement profileLogo;

    //Локатор текста о персональных данных
    @FindBy(how = How.CLASS_NAME, using = "Account_text__fZAIn")
    private SelenideElement profileText;

    //Локатор кнопки "Выход"
    @FindBy(how = How.CLASS_NAME, using = "Account_button__14Yp3")
    private SelenideElement profileButtonExit;

    //Метод для получения текста ссылки на персональные данные
    @Step("Get profile button text")
    public String getProfileText() {
        return profileText.getText();
    }

    //Метод для клика по кнопке "Конструктор"
    @Step("Click constructor button")
    public void clickButtonConstructor() {
        profileButtonConstructor.click();
    }

    //Метод для клика по логотипу
    @Step("Logo click")
    public void clickLogo() {
        profileLogo.click();
    }

    //Метод для клика по кнопке "Выход"
    @Step("Click exit button")
    public void clickExit() {
        profileButtonExit.click();
    }

}
