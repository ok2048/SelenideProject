package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.t1.selenide.tests.constant.Constants.BASE_URL;

public class DisappearingElementsUiTest {
    private static final SelenideElement disappearingElementsButton = $x("//a[@href='/disappearing_elements']");
    private final ElementsCollection elements = $$x("//li");

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
        open(BASE_URL);
        disappearingElementsButton.should(visible).click();
    }

    @Epic("Herokuapp UI tests")
    @Feature("Disappearing Elements tests")
    @DisplayName("Positive scenario Disappearing elements test")
    @RepeatedTest(value = 10, name = "repetition {currentRepetition} of {totalRepetitions}")
    void disappearingElementsPositive() {
        refresh();
        elements.should(size(5));
    }

    @AfterAll
    static void tearDown() {
        closeWindow();
    }

}
