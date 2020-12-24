package com.dropbox.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    @FindBy(css = "button.action-new-folder")
    private SelenideElement newFolderButton;

    @FindBy(id = "continuous-onboarding-collapse-btn")
    private SelenideElement onboardingButton;

    @FindBy(css = ".account-menu-v2__avatar")
    private SelenideElement accountMenu;

    public CreateFolderPage clickOnCreateNewFolderButton() {
        if (onboardingButton.is(appear)) {
            onboardingButton.click();
        }
        newFolderButton.shouldBe(appear);
        newFolderButton.click();
        return page(CreateFolderPage.class);
    }

    public HomePage uploadFile(String fileName) {
        if (onboardingButton.is(appear)) {
            onboardingButton.click();
        }
        $("$('[type=file]')").uploadFromClasspath(fileName);
        return this;
    }

    public LoginPage logout() {
        accountMenu.click();
        $(By.xpath("/html/body/div[18]/div/nav/div/div[2]/div[2]")).click();
        return page(LoginPage.class);
    }

}
