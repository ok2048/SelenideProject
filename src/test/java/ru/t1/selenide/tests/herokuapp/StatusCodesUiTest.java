package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class StatusCodesUiTest extends BaseUiTest {
    private final SelenideElement statusCodesButton = $x("//a[@href='/status_codes']");
    private final String STATUS_LINK_XPATH = "//a[@href='status_codes/%d']";
    private final SelenideElement statusCodeText = $x("//h3/following-sibling::p");

    // Вместо нескольких тестовых методов решила создать один параметризованный
    @ParameterizedTest(name = "{index} - {0} status code UI test")
    @ValueSource(ints = {200, 301, 404, 500})
    void statusCodeUiPositive(int statusCode) {
        statusCodesButton.should(visible).click();

        SelenideElement statusLink = $x(STATUS_LINK_XPATH.formatted(statusCode));
        statusLink.should(visible).click();
        System.out.println(statusCodeText.text());
    }
}
