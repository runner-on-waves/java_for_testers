package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {
    @Test
    void canRegisterUser() {
        //создаем пользователя с рандомным именем
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost",username);
        var password = "password"; // заполняем пароль константой
        //создать пользователя(адрес) на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(email, "password");
        //заполняем форму создания и отправляем (браузер)
        app.signup().register(username,email);
        //ждём почту (MailHelper)
        var messages = app.mail().receive(email,password, Duration.ofSeconds(60));
        //извлекаем ссылку из письма
        var url = getUrl(messages.get(0).content());
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
       app.signup().confirmRegistration(url, username,password);
        // проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
