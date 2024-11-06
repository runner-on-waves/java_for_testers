package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {
    DeveloperMailUser user;

    //вариант с использованием smtp сервера
    @Test
    void canCreateUser() {
        //создаем пользователя с рандомным именем
        // var username = CommonFunctions.randomString(8);
        var password = "password"; // заполняем пароль константой
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        //заполняем форму создания и отправляем (браузер)
        app.signup().createUser(user.name(),email);
        //ждём почту (MailHelper)
        var message = app.developerMail().receive(user, Duration.ofSeconds(30));
        //извлекаем ссылку из письма
        var url = getUrl(message);
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
       app.signup().confirmRegistration(url, user.name(), password);
        // проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }



//    @AfterEach
//    void deleteMailUser() {
//        app.developerMail().deleteUser(user);
//    }

 //Регистрация нового пользователя с JamesApi  и параметризованным тестом
    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canRegisterUser(String username) {
        //создаем пользователя с рандомным именем
        var email = String.format("%s@localhost", username);
        var password = "password"; // заполняем пароль константой
        //создать пользователя(адрес) на почтовом сервере (JamesHelper)
        app.jamesApi().addUser(email, "password");
        //заполняем форму создания и отправляем (браузер)
        app.signup().register(username, email);
        //ждём почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        //извлекаем ссылку из письма
        var url = getUrl(messages.get(0).content());
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.signup().confirmRegistration(url, username, password);
        // проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
