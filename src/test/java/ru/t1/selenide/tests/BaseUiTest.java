package ru.t1.selenide.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseUiTest {

    protected static final String BASE_URL = "https://the-internet.herokuapp.com/";

    @BeforeEach
    void setup() {
        open(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

}
