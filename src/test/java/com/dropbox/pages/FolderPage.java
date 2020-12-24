package com.dropbox.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FolderPage {

    @FindBy(css = "h2[data-testid='breadcrumb-segment']")
    private SelenideElement folderName;

    public FolderPage isLoaded() {
        folderName.shouldBe(visible);
        return this;
    }


    public void shouldIncludeFolderName(String name) {
        assertThat(folderName.getText(), equalTo(name));
    }

}
