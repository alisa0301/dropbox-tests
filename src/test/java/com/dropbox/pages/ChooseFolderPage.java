package com.dropbox.pages;

import com.codeborne.selenide.SelenideElement;
import com.dropbox.config.Config;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class ChooseFolderPage {
    @FindBy(css = ".mc-media-cell-text-title")
    private List<SelenideElement> folders;

    @FindBy(css = ".dig-Modal-footer button.dig-Button--primary")
    private SelenideElement submitButton;

    public HomePage chooseFolder() {
        submitButton.shouldBe(visible);
        folders.stream()
                .filter(element -> Config.getString("dropbox.folder.default").equals(element.getText().trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("someone delete default folder"))
                .click();
        submitButton.click();
        return page(HomePage.class);
    }
}
