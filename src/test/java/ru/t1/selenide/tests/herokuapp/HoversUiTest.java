package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HoversUiTest extends BaseUiTest {
    private final SelenideElement hoversButton = $x("//a[@href='/hovers']");
    private final ElementsCollection images = $$x("//img[@alt='User Avatar']");
    private final ElementsCollection captions = $$x("//div[@class='figcaption']/h5");
    private final ElementsCollection profileLinks = $$x("//div[@class='figcaption']/a");

    @Test
    void hoversPositive() {
        hoversButton.should(visible).click();

        images.forEach(img -> {
            img.should(visible).hover();
            System.out.println(captions.filter(visible).first().text());
            System.out.println(profileLinks.filter(visible).first().text());
        });
    }
}
