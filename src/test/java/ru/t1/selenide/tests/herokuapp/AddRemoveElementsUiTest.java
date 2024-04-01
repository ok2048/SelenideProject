package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import ru.t1.selenide.tests.BaseUiTest;
import ru.t1.selenide.tests.constant.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AddRemoveElementsUiTest extends BaseUiTest {
    private final SelenideElement addRemoveElementsButton = $x("//a[@href='/add_remove_elements/']");
    private final SelenideElement addElementButton = $x("//button[@onclick='addElement()']");
    private final ElementsCollection deleteElementButtons = $$x("//button[@onclick='deleteElement()']");

    @Epic("Herokuapp UI tests")
    @Feature("Add/Remove Elements tests")
    @TestFactory
    List<DynamicTest> addRemoveElementsPositive() {
        List<DynamicTest> result = new ArrayList<>();
        List<Integer> toAdd = List.of(2, 5, 1);
        List<Integer> toDelete = List.of(1, 2, 3);

        for (int i = 0; i < toAdd.size(); i++) {
            final int index = i;
            result.add(DynamicTest.dynamicTest(
                    "Positive scenario Add/remove - %d:%d".formatted(toAdd.get(i), toDelete.get(i)),
                    () -> {
                        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                                .screenshots(true)
                                .savePageSource(true)
                                .includeSelenideSteps(true));
                        Selenide.open(Constants.BASE_URL);
                        addRemoveElementsSteps(toAdd.get(index), toDelete.get(index));
                        closeWindow();
                    }
            ));
        }
        return result;
    }

    void addRemoveElementsSteps(int toAdd, int toDelete) {
        addRemoveElementsButton.should(visible).click();

        for (int i = 0; i < toAdd; i++) {
            addElementButton.should(visible).click();
            deleteElementButtons.should(size(i + 1));
        }

        deleteElementButtons.should(size(toAdd));
        for (int i = 0; i < toDelete; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(deleteElementButtons.size());
            deleteElementButtons.get(randomIndex).click();
            deleteElementButtons.should(size(toAdd - i - 1));
        }
    }

}
