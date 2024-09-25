package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }
    protected void selectMonth(ContactData contact) {
        manager.driver.findElement(By.xpath(String.format("//option[contains(.,'%s')]", contact.monthOfBirth()))).click();
    }
}
