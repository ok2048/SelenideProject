package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWindow;
import static ru.t1.selenide.tests.constant.Constants.BASE_URL;
import static ru.t1.selenide.tests.helper.DataGenerator.generateTestData;

public class InputsUiTest {
    private final SelenideElement inputsButton = $x("//a[@href='/inputs']");
    private final SelenideElement input = $x("//input[@type='number']");

    @Epic("Herokuapp UI tests")
    @Feature("Inputs tests")
    @TestFactory
    List<DynamicTest> inputsPositive() {
        List<DynamicTest> result = new ArrayList<>();
        List<Integer> testData = generateTestData(10);

        for (int i = 0; i < testData.size(); i++) {
            final int index = i;
            result.add(DynamicTest.dynamicTest(
                    "Positive scenario Input - \"%d\"".formatted(testData.get(i)),
                    () -> {
                        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                                .screenshots(true)
                                .savePageSource(true)
                                .includeSelenideSteps(true));
                        Selenide.open(BASE_URL);
                        inputsButton.should(visible).click();
                        input.should(visible).sendKeys("" + testData.get(index));
                        input.should(value("" + testData.get(index)));
                        closeWindow();
                    }
            ));
        }
        return result;
    }

}
