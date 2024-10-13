package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("").withPhoto(randomFile("src/test/resources/images")));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveAllContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("").withPhoto(randomFile("src/test/resources/images")));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

    @Test
    public void canRemoveContactFromGroup() {
        GroupData group;
        ContactData contact;
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "new name", "new header", "new footer"));
        }
        group = app.hbm().getGroupList().get(0);
        if (app.hbm().getContactCount() == 0) {
            contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(15))
                    .withLastName(CommonFunctions.randomString(20))
                    .withAddress(CommonFunctions.randomString(30))
                    .withPhoto(randomFile("src/test/resources/images"));
            app.contacts().createContact(contact,group);
        } else {
            contact = app.hbm().getContactList().get(0);
            app.contacts().addContactToGroup(group,contact);
        }
        var oldRelated =  app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated =  app.hbm().getContactsInGroup(group);
        var uiRelated = app.contacts().getGroupContacts(group);
        boolean result = compareGroupContactLists(newRelated,uiRelated);
        Assertions.assertEquals(oldRelated.size()-1, newRelated.size());
        Assertions.assertTrue(result);
    }
    
}


