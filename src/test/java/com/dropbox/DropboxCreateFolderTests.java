package com.dropbox;

import com.dropbox.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;


public class DropboxCreateFolderTests extends BaseTests {

    private static final String BASE_URL = Config.getString("dropbox.base.url");


    @Test
    @DisplayName("Folder Creation")
    void createFolderTest() {
        String folderName = "my_awesome_folder_42";

        loginToDropboxByDefaultUser()
                .clickOnCreateNewFolderButton()
                .createFolderWithName(folderName)
                .isLoaded()
                .shouldIncludeFolderName(folderName);

        assertThat(getWebDriver().getCurrentUrl(), startsWith(BASE_URL + "/home/my_awesome_folder_42"));
    }

    @Test
    @DisplayName("Should create Folder with already existed name")
    void createFolderWithAlreadyExistedTest() {
        loginToDropboxByDefaultUser()
                .clickOnCreateNewFolderButton()
                .createFolderWithName(Config.getString("dropbox.folder.default"))
                .isLoaded();

        assertThat(getWebDriver().getCurrentUrl(), startsWith(BASE_URL + "home/alice20"));
    }
}
