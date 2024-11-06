package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);

    }

    public void register(String user, String email) {
        click(By.xpath("//a[@href='signup_page.php']"));
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type = 'submit']"));
    }

    public void createUser(String user, String email) {
        type(By.name("username"), manager.property("web.username"));
        click(By.cssSelector("input[type = 'submit']"));
        type(By.name("password"), manager.property("web.password"));
        click(By.cssSelector("input[type = 'submit']"));
        click(By.cssSelector("i[class = 'fa fa-gears menu-icon']"));
        click(By.xpath("//a[@href='/mantisbt-2.27.0/manage_user_page.php']"));
        click(By.xpath("//a[@href='manage_user_create_page.php']"));
        type(By.name("username"), user);
        type(By.name("realname"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type = 'submit']"));
    }

    public void confirmRegistration(String url, String user, String password) {
        manager.driver().get(url);
        type(By.name("realname"), user);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type = 'submit']"));
    }
}