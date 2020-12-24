package com.dropbox;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;


public class DropboxCreateFolderTests extends BaseTests {


    @Test
    @DisplayName("Folder Creation")
    void createFolderTest() {
        loginToDropboxByDefaultUser()
                .clickOnCreateNewFolderButton()
                .createFolderWithName("my_awesome_folder_42");

        assertThat(getWebDriver().getCurrentUrl(), startsWith("https://www.dropbox.com/home/my_awesome_folder_42"));
    }

    @Test
    @DisplayName("Should create Folder with already existed name")
    void createFolderWithAlreadyExistedTest() {
        loginToDropboxByDefaultUser()
                .clickOnCreateNewFolderButton()
                .createFolderWithName("alice");

        assertThat(getWebDriver().getCurrentUrl(), startsWith("https://www.dropbox.com/home/alice20"));
    }
}
