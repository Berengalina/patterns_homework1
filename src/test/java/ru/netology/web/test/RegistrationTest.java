package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @Test
    void shouldRegisterCardDelivery(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(DataHelper.generateCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(DataHelper.generateFirstDateOfMeeting());
        $("[data-test-id=name] input").setValue(DataHelper.generateByFakerName("ru"));
        $("[data-test-id=phone] input").setValue(DataHelper.generateByFakerPhone("ru"));
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5)).shouldHave(text("Встреча успешно запланирована на " + DataHelper.generateFirstDateOfMeeting()));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(DataHelper.generateSecondDateOfMeeting());
        $(byText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5)).shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5)).shouldHave(text("" + DataHelper.generateSecondDateOfMeeting()));
    }

    @Test
    void shouldNotRegisterByName(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(DataHelper.generateCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(DataHelper.generateFirstDateOfMeeting());
        $("[data-test-id=name] input").setValue(DataHelper.generateByFakerName("de"));
        $("[data-test-id=phone] input").setValue(DataHelper.generateByFakerPhone("ru"));
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

//    @Test
//    void shouldNotRegisterByPhone(){
//        open("http://localhost:9999/");
//        $("[data-test-id=city] input").setValue(DataHelper.generateCity());
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
//        $("[data-test-id=date] input").setValue(DataHelper.generateFirstDateOfMeeting());
//        $("[data-test-id=name] input").setValue(DataHelper.generateByFakerName("ru"));
//        $("[data-test-id=phone] input").setValue(DataHelper.generateByFakerDigit());
//        $("[data-test-id=agreement]").click();
//        $(byText("Запланировать")).click();
//        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Ошибка"));
//    }
//
//    @Test
//    void shouldRegisterWithSymbolName(){
//        open("http://localhost:9999/");
//        $("[data-test-id=city] input").setValue(DataHelper.generateCity());
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
//        $("[data-test-id=date] input").setValue(DataHelper.generateFirstDateOfMeeting());
//        $("[data-test-id=name] input").setValue(DataHelper.setNameWithSymbol());
//        $("[data-test-id=phone] input").setValue(DataHelper.generateByFakerPhone("ru"));
//        $("[data-test-id=agreement]").click();
//        $(byText("Запланировать")).click();
//        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5)).shouldHave(text("Встреча успешно запланирована на " + DataHelper.generateFirstDateOfMeeting()));
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
//        $("[data-test-id=date] input").setValue(DataHelper.generateSecondDateOfMeeting());
//        $(byText("Запланировать")).click();
//        $("[data-test-id=replan-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5)).shouldHave(text("У вас уже запланирована встреча на другую дату"));
//        $(byText("Перепланировать")).click();
//        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5)).shouldHave(text("" + DataHelper.generateSecondDateOfMeeting()));
//    }
//


}
