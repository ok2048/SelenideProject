package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.t1.selenide.tests.constant.Constants.BASE_URL;

public class NotificationMessagesUiTest {
    private static final SelenideElement notificationMessagesButton =
            $x("//a[@href='/notification_message']");
    private final SelenideElement clickArea = $x("//a[@href='/notification_message']");
    private final SelenideElement alertMessage = $x("//div[@id='flash']");
    private final SelenideElement closeAlertButton = $x("//a[@href='#' and @class='close']");

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
        open(BASE_URL);
        notificationMessagesButton.should(visible).click();
    }

    @Epic("Herokuapp UI tests")
    @Feature("Notification Messages tests")
    @DisplayName("Positive scenario Notification Messages tests")
    @RepeatedTest(value = 10, name = "repetition {currentRepetition} of {totalRepetitions}")
    void notificationMessagesPositive() {
        closeAlertButton.should(visible).click();
        clickArea.should(visible).click();
        String message = alertMessage.should(visible).getOwnText().trim();
        Assertions.assertThat(message)
                .as("Check that message is \"Action successful\"")
                .isEqualTo("Action successful");
    }

    @AfterAll
    static void tearDown() {
        closeWindow();
    }
}
