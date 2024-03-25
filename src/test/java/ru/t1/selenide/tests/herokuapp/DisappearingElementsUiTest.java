package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DisappearingElementsUiTest extends BaseUiTest {
    private final SelenideElement disappearingElementsButton = $x("//a[@href='/disappearing_elements']");
    private final ElementsCollection elements = $$x("//li");

    @Test
    void disappearingElementsPositive() {
        disappearingElementsButton.should(visible).click();

        int tries = 0;
        int numberOfElements = elements.should(CollectionCondition.sizeGreaterThan(0)).size();

        while (numberOfElements != 5 && tries < 10) {
            refresh();
            numberOfElements = elements.should(CollectionCondition.sizeGreaterThan(0)).size();
            tries++;
        }

        Assertions.assertEquals(5, numberOfElements);
    }
}
