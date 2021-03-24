package ru.netology;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class GeneratorData {
    private static RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void RegUser(User regDto) {
        given()
                .spec(request)
                .body(regDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static User genValidActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        User validUser = new User(
                faker.name().username().toLowerCase(),
                faker.internet().password(),
                "active"
        );
        RegUser(validUser);
        return validUser;
    }

    public static User genValidBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        User blockedUser = new User(
                faker.name().username().toLowerCase(),
                faker.internet().password(),
                "blocked"
        );
        RegUser(blockedUser);
        return blockedUser;
    }

    public static User genInvalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        String password = faker.internet().password();
        User invalidLogin = new User(
                "username",
                password,
                "active"
        );
        RegUser(invalidLogin);
        return new User("badLogin", password, "active");
    }

    public static User genBadPassword() {
        Faker faker = new Faker(new Locale("en"));
        String userName = faker.name().username().toLowerCase();
        User badPassword = new User(
                userName,
                "correctPassword",
                "active"
        );
        RegUser(badPassword);
        return new User(userName, "badPassword", "active");
    }

}
