package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithEmptyFields() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstName() {
        app.contacts().createContact(new ContactData().withFirstName("Pikachu"));
    }

    @Test
    public void canCreateContactWithLastName() {
        app.contacts().createContact(new ContactData().withLastName("Greeny"));
    }

    @Test
    public void canCreateContactWithAddress() {
        app.contacts().createContact(new ContactData().withAddress("Gagarina Street 24"));
    }

    @Test
    public void canCreateContactWithHomePhone() {
        app.contacts().createContact(new ContactData().withHomePhone("+7955111111111"));
    }

    @Test
    public void canCreateContactWithMobilePhone() {
        app.contacts().createContact(new ContactData().withMobilePhone("+7999111111122"));
    }

    @Test
    public void canCreateContactWithWorkPhone() {
        app.contacts().createContact(new ContactData().withWorkPhone("+7999111111133"));
    }

    @Test
    public void canCreateContactWithAllPhones() {
        app.contacts().createContact(new ContactData().withAllPhones("+7999111111111", "+7999111111122", "+7999111111133"));
    }

    @Test
    public void canCreateContactWithEmail() {
        app.contacts().createContact(new ContactData().withEmail("pokemon@mail.ru"));
    }

    @Test
    public void canCreateContactWithEmail2() {
        app.contacts().createContact(new ContactData().withEmail2("pokemon2@mail.ru"));
    }

    @Test
    public void canCreateContactWithEmail3() {
        app.contacts().createContact(new ContactData().withEmail3("pokemon3@mail.ru"));
    }

    @Test
    public void canCreateContactWithAllEmails() {
        app.contacts().createContact(new ContactData().withAllEmails("kolobok@mail.ru", "kolobok2@mail.ru", "kolobok3@mail.ru"));
    }

    // поля не отображается на странице с контактами, только в адресной книге
    @Test
    public void canCreateContactWithMiddleName() {
        app.contacts().createContact(new ContactData().withMiddleName("Petrovich"));
    }

    @Test
    public void canCreateContactWithNickName() {
        app.contacts().createContact(new ContactData().withNickName("Pyatachok"));
    }

    @Test
    public void canCreateContactWithFax() {
        app.contacts().createContact(new ContactData().withFax("+7999111111144"));
    }

    @Test
    public void canCreateContactWithMonthOfBirth() {
        app.contacts().createContact(new ContactData().withMonthOfBirth("September"));
    }

    @Test
    public void canCreateContactWithYearOfBirth() {
        app.contacts().createContact(new ContactData().withYearOfBirth("2000"));
    }

    @Test
    public void canCreateContactWithGroup() {
        app.contacts().createContactWithGroup(new ContactData());
    }

    @Test
    public void canCreateContactWithCompany() {
        app.contacts().createContactWithGroup(new ContactData().withCompany("Winnie-Pooh"));
    }


}
