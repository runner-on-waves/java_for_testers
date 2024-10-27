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
    public void confirmRegistration(String url,String user, String password) {
        manager.driver().get(url);
        type(By.name("realname"), user);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type = 'submit']"));
    }
}