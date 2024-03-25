package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CheckboxUiTest extends BaseUiTest {
    private final SelenideElement checkboxButton = $x("//a[@href='/checkboxes']");
    private final ElementsCollection checkboxes = $$x("//input[@type='checkbox']");

    @Test
    void CheckBoxPositive() {
        checkboxButton.should(visible).click();

        //Ввиду простоты примера проще обойтись без forEach, циклов и т.д.
        if (checkboxes.first().should(visible).getAttribute("checked") == null) {
            checkboxes.first().click();
        }
        if (checkboxes.last().should(visible).getAttribute("checked") != null) {
            checkboxes.last().click();
        }
        System.out.printf("checkbox1.checked = %s%n", checkboxes.first().getAttribute("checked"));
        System.out.printf("checkbox2.checked = %s%n", checkboxes.last().getAttribute("checked"));
    }
}
