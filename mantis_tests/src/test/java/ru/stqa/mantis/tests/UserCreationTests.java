package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase {
    //вариант с использованием smtp сервера, для проведения теста необходимо изменить настройки в config_inc.php c localhost на smptp сервер
    //DeveloperMailUser user;
//    @Test
//    void canCreateUser() {
//        //создаем пользователя с рандомным именем
//        // var username = CommonFunctions.randomString(8);
//        var password = "password"; // заполняем пароль константой
//        user = app.developerMail().addUser();
//        var email = String.format("%s@developermail.com", user.name());
//        //заполняем форму создания и отправляем (браузер)
//        app.signup().createUser(user.name(),email);
//        //ждём почту (MailHelper)
//        var message = app.developerMail().receive(user, Duration.ofSeconds(30));
//        //извлекаем ссылку из письма
//        var url = getUrl(message);
//        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
//       app.signup().confirmRegistration(url, user.name(), password);
//        // проверяем что пользователь может залогиниться (HttpSessionHelper)
//        app.http().login(user.name(), password);
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }

//    @AfterEach
//    void deleteMailUser() {
//        app.developerMail().deleteUser(user);
//    }

    //Создание нового пользователя с James Api  и Mantis Api
    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUserWithAPI(String username) {
        var email = String.format("%s@localhost", username);
        var password = "p@ssw0rd"; // заполняем пароль константой
        //создать пользователя(адрес) на почтовом сервере (JamesHelper) через API
        app.jamesApi().addUser(email, password);
        //заполняем объект UserData
        var userData = new UserData()
                .withRealName(username)
                .withUserName(username)
                .withEmail(email)
                .withPassword(password)
                .withEnabled(true)
                .withProtected(false)
                .withAccessLevel("reporter");
        // Отправляем POST запрос на создание пользователя REST API Mantis
        app.rest().createUser(userData);
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

    //Создание нового пользователя с James Api  и Mantis UI
    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUser(String username) {
        //создаем пользователя с рандомным именем
        var email = String.format("%s@localhost", username);
        var password = "password"; // заполняем пароль константой
        //создать пользователя(адрес) на почтовом сервере (JamesHelper)
        app.jamesApi().addUser(email, "password");
        //создаем пользователя администратором через форму создания(браузер)
        app.signup().createUser(username, email);
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
