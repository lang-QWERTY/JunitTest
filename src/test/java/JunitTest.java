package JunitTest;


import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class JunitTest {

    @BeforeEach
    void setUp(){
        open("https://codered.su/");
    }

    @ValueSource(strings = {
            "Куртка", "Футболка", "Лонгслив"
    })
    @ParameterizedTest (name = "Найти одежду")
    @Tag("Test")

    void lookingforclothes(String cloth) {
        $("input[placeholder='поиск товара']").shouldBe(visible).setValue(cloth).pressEnter();
        $("body").shouldHave(text(cloth));
    }

    @CsvSource(value = {
            "Футболка, Футболка Regular",
            "Куртка, Куртка Puffed"
    })
    @ParameterizedTest(name = "Ищим шмот [0], нужный шмот [1]")
    @Tag("BLOCKER")
    void lookingforcertainitems(String wear, String expectedArrive) {
        $("input[placeholder='поиск товара']").shouldBe(visible).setValue(wear).pressEnter();
        $(".name_wrap").shouldHave(text(expectedArrive));
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "При выборе языка {0} отображается корректный плэйсхолдер")
    @Tag("BLOCKER")
    void changeTheLanguageOnTheGear(Language language) {
        $$(".lang_icon").find(text(language.name())).click();
        $$(".head").findBy(visible).shouldHave(text(language.description));
    }
}



