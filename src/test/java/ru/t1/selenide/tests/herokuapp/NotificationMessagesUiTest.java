package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class NotificationMessagesUiTest extends BaseUiTest {
    private final SelenideElement notificationMessagesButton = $x("//a[@href='/notification_message']");
    private final SelenideElement clickArea = $x("//a[@href='/notification_message']");
    private final SelenideElement alertMessage = $x("//div[@id='flash']");
    private final SelenideElement closeAlertButton = $x("//a[@href='#' and @class='close']");

    @Test
    void notificationMessagesPositive() {
        notificationMessagesButton.should(visible).click();

        String message = alertMessage.should(visible).getOwnText().trim();
        // Если при переходе на страницу сообщение сразу "Action successful", кликать не будем
        while (!"Action successful".equals(message)) {
            closeAlertButton.should(visible).click();
            clickArea.should(visible).click();
            message = alertMessage.should(visible).getOwnText().trim();
        }
    }
}
