package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContactWithGroup(ContactData contact) {
        openContactPage();
        fillContactForm(contact);
        selectGroupContact();
        submitContactCreation();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.nickname());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("byear"), contact.year());
        type(By.name("company"), contact.company());
        selectMonth(contact);

    }

    public void openContactPage() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        returnToHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        returnToHomePage();
        selectContact();
        removeSelectedContact();
        //manager.driver.switchTo().alert().accept();
        returnToHomePage();
    }

    public void removeAllContacts() {
        returnToHomePage();
        selectContact();
        click(By.xpath("//input[@id=\'MassCB\']"));
        removeSelectedContact();
        //manager.driver.switchTo().alert().accept();
        returnToHomePage();
    }

    private void removeSelectedContact() {
        {
            click(By.xpath("//input[@value=\'Delete\']"));
        }
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void selectGroupContact() {
        click(By.name("new_group"));
        click(By.xpath("//option[1]"));
    }

    public int getCount() {
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
