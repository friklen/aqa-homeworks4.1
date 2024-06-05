package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));


        @Test
        void shouldBookCard() {
            open("http://localhost:9999");
            $("[data-test-id='city'] input").setValue("Москва");
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
            $("[data-test-id='date'] input").sendKeys(meetingDate);
            $("[data-test-id='name']").$("input").setValue("Петров-Водкин Константин");
            $("[data-test-id='phone'] input").setValue("+79201111111");
            $("[data-test-id='agreement']").click();
            $$("button").find(exactText("Забронировать")).click();
            $(".notification__content").shouldBe(visible, Duration.ofMillis(15000));
            $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + meetingDate));
        }


    }



