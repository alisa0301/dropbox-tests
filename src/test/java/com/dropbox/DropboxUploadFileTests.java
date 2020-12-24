package com.dropbox;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DropboxUploadFileTests extends BaseTests {


    @ParameterizedTest(name = "{index} upload file {0}")
    @ValueSource(strings = {"galaxy.jpg", "giphy-4.gif", "stdout (9)"})
    @DisplayName("Upload correct file")
    void uploadFileTest(String fileName) {
        loginToDropboxByDefaultUser()
                .uploadFile(fileName);

    }
}
