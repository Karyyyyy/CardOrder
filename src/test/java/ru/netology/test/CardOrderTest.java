package ru.netology.test;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    @Test
    void completedForm(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Вася Пупкин");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void invalidSurnameAndName(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Vasya Pupkin");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=name] span[class=input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void invalidPhoneNumber() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Вася Пупкин");
        $("[data-test-id=phone] input").setValue("123456789");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=phone] span[class=input__sub]").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    }

