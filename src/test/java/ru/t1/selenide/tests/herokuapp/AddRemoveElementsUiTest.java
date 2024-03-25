package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AddRemoveElementsUiTest extends BaseUiTest {
    private final SelenideElement addRemoveElementsButton = $x("//a[@href='/add_remove_elements/']");
    private final SelenideElement addElementButton = $x("//button[@onclick='addElement()']");
    private final ElementsCollection deleteElementButtons = $$x("//button[@onclick='deleteElement()']");

    @Test
    void addRemoveElementsPositive() {
        addRemoveElementsButton.should(visible).click();

        for (int i = 0; i < 5; i++) {
            addElementButton.should(visible).click();
            System.out.println(
                    deleteElementButtons.should(CollectionCondition.sizeGreaterThan(0)).last().text()
            );
        }

        int numberOfButtons = deleteElementButtons.should(CollectionCondition.sizeGreaterThan(0)).size();
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(numberOfButtons);
            deleteElementButtons.get(randomIndex).click();
            numberOfButtons = deleteElementButtons.should(CollectionCondition.sizeGreaterThan(0)).size();
            System.out.printf("There are %d buttons:%n", numberOfButtons);
            deleteElementButtons.forEach(button -> System.out.println(button.text()));
        }
    }

}
