package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class InputsUiTest extends BaseUiTest {
    private final SelenideElement inputsButton = $x("//a[@href='/inputs']");
    private final SelenideElement input = $x("//input[@type='number']");

    @Test
    void inputsPositive() {
        inputsButton.should(visible).click();

        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1;

        input.should(visible).sendKeys("" + randomNumber);
        System.out.printf("input.value = %s%n", input.val());
    }
}
