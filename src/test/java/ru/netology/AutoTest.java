package ru.netology;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutoTest {
    @Test
    void shouldSubmitWhenValidUser() {
        User user = GeneratorData.genValidActiveUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldSubmitWhenBlockedUser() {
        User user = GeneratorData.genValidBlockedUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Пользователь заблокирован")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldSubmitWhenInvalidUser() {
        User user = GeneratorData.genInvalidLogin();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Неверно указан логин или пароль")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldSubmitWhenBadPassword() {
        User user = GeneratorData.genBadPassword();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Неверно указан логин или пароль")).shouldBe(visible, Duration.ofSeconds(5));
    }
}
