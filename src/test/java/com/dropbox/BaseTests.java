package com.dropbox;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.dropbox.config.Config;
import com.dropbox.pages.HomePage;
import com.dropbox.pages.LoginPage;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTests {

    @BeforeEach
    public void init() {
        closeWebDriver();
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    public void tearDown() throws IOException {
        screenshot();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }


    static LoginPage openLoginPage() {
        return open("https://www.dropbox.com/login", LoginPage.class);
    }

    static HomePage loginToDropboxByDefaultUser() {
        String login = Config.getString("login");
        String password = Config.getString("password");
        return openLoginPage().enterCredentials(login, password);
    }
}
