package com.dropbox;

import com.dropbox.config.Config;
import com.dropbox.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DropboxLoginLogoutTests extends BaseTests {

    @Test
    @DisplayName("Login/Logout with right creds")
    void successLoginLogoutTest() {
        String login = Config.getString("login");
        String password = Config.getString("password");

        HomePage homePage = openLoginPage().enterCredentials(login, password);
        assertThat(getWebDriver().getCurrentUrl(), equalTo("https://www.dropbox.com/h"));
        homePage.logout();
        assertThat(getWebDriver().getCurrentUrl(), equalTo("https://www.dropbox.com/login?src=logout"));
    }

    @Test
    @DisplayName("Error after incorrect email")
    void incorrectEmailTest() {
        openLoginPage().enterCredentials("alala11111111@mail.ru", "alala");
        assertThat(getWebDriver().getCurrentUrl(), equalTo("https://www.dropbox.com/login"));
        $(".login-email .error-message").shouldBe(visible);
        $(".c-card--error").shouldBe(visible);
    }

    @Test
    @DisplayName("Error after incorrect password")
    void incorrectPasswordTest() {
        String login = Config.getString("login");
        openLoginPage().enterCredentials(login, "alala");
        assertThat(getWebDriver().getCurrentUrl(), equalTo("https://www.dropbox.com/login"));
        $(".login-email .error-message").shouldBe(visible);
        $(".c-card--error").shouldBe(visible);
    }

    @ParameterizedTest(name = "{index} set value \"{0}\"")
    @ValueSource(strings = {"my_awesome_test", "my_awesome_testmail.ru", "my_awesome@", "@", "42"})
    @DisplayName("Prompt to incorrect email")
    void notAnEmailTest(String mail) throws Exception {
        openLoginPage()
                .enterCredentials(mail, "alala");
        assertThat(getWebDriver().getCurrentUrl(), equalTo("https://www.dropbox.com/login"));
        $(".login-email .error-message").shouldBe(visible);
        $(".c-card--error").shouldBe(visible);
    }
}
