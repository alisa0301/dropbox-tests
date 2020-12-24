package com.dropbox.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class CreateFolderPage {

    @FindBy(id = "new_folder_name_input")
    private SelenideElement folderNameInput;

    @FindBy(id = ".dig-Modal-footer button.dig-Button--primary")
    private SelenideElement createFolderButton;

    public HomePage createFolderWithName(String name) {
        folderNameInput.setValue(name);
        createFolderButton.click();
        return page(HomePage.class);
    }
}
