package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DropdownUiTest extends BaseUiTest {
    private final SelenideElement dropdownButton = $x("//a[@href='/dropdown']");
    private final SelenideElement dropdown = $x("//select[@id='dropdown']");
    private final SelenideElement option1 = $x("//option[@value='1']");
    private final SelenideElement option2 = $x("//option[@value='2']");

    @Test
    void dropdownPositive() {
        dropdownButton.should(visible).click();

        dropdown.should(visible).click();
        option1.should(visible).click();
        System.out.printf("dropdown.text = '%s'%n", dropdown.text());

        dropdown.should(visible).click();
        option2.should(visible).click();
        System.out.printf("dropdown.text = '%s'%n", dropdown.text());
    }
}
