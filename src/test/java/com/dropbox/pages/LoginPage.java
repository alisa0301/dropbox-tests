package com.dropbox.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    @FindBy(css = "[type ='email']")
    private SelenideElement login;

    @FindBy(css = "[type ='password']")
    private SelenideElement password;

    @FindBy(css = ".signin-button")
    private SelenideElement signInButton;

    public HomePage enterCredentials(String login, String password) {
        this.login.setValue(login);
        this.password.setValue(password);
        this.signInButton.click();
        return page(HomePage.class);
    }

    public LoginPage isLoaded() {
        login.shouldBe(Condition.visible);
        password.shouldBe(Condition.visible);
        signInButton.shouldBe(Condition.visible);
        return this;
    }

}
