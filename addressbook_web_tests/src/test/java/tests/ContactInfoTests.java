package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(20))
                    .withAddress(CommonFunctions.randomString(30))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testContactDataModification() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(20))
                    .withAddress(CommonFunctions.randomString(30))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        var modifiedContact = app.contacts().getList().get(0);
        var id = modifiedContact.id();
        var modifiedAddress = CommonFunctions.randomString(15);
        var modifiedEmail = CommonFunctions.randomString(10);
        var modifiedEmail2 = CommonFunctions.randomString(10);
        var modifiedEmail3 = CommonFunctions.randomString(10);
        var modifiedEmails = modifiedEmail + "\n" +modifiedEmail2 +"\n" +modifiedEmail3;
        var modifiedMobile = CommonFunctions.randomString(12);
        var modifiedHome = CommonFunctions.randomString(12);
        var modifiedWork = CommonFunctions.randomString(12);
        var modifiedPhones = modifiedHome+ "\n" +modifiedMobile + "\n"+modifiedWork;
        var testData = new ContactData().withAddress(modifiedAddress)
                .withEmail(modifiedEmail)
                .withEmail2(modifiedEmail2)
                .withEmail3(modifiedEmail3)
                .withMobilePhone(modifiedMobile)
                .withHomePhone(modifiedHome)
                .withWorkPhone(modifiedWork);
        app.contacts().modifyContact(modifiedContact, testData);
        var address = app.contacts().getAddress().get(id);
        var emails =  app.contacts().getEmails().get(id);
        var phones = app.contacts().getPhones().get(id);
        Assertions.assertEquals(modifiedAddress, address);
        Assertions.assertEquals(modifiedEmails, emails);
        Assertions.assertEquals(modifiedPhones, phones);
    }
}
