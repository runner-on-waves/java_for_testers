package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithEmptyFields(){
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstName(){
        app.contacts().createContact(new ContactData().withFirstName("Pikachu"));
    }

    @Test
    public void canCreateContactWithLastName(){
        app.contacts().createContact(new ContactData().withLastName("Greeny"));
    }
    @Test
    public void canCreateContactWithAddress(){
        app.contacts().createContact(new ContactData().withAddress("Gagarina Street 24"));
    }
    @Test
    public void canCreateContactWithMobilePhone(){
        app.contacts().createContact(new ContactData().withMobilePhone("+7999111111111"));
    }
    @Test
    public void canCreateContactWithEmail2(){
        app.contacts().createContact(new ContactData().withEmail2("pokemon2@mail.ru"));
    }

    @Test
    public void canCreateContactWithEmail3(){
        app.contacts().createContact(new ContactData().withEmail3("pokemon3@mail.ru"));
    }

    @Test
    public void canCreateContactWithYearOfBirth(){
        app.contacts().createContact(new ContactData().withYearOfBirth("2000"));
    }
    @Test
    public void canCreateContactWithGroup(){
        app.contacts().createContactWithGroup(new ContactData());
    }
}
