package tests;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{
    @Test
    void canModifyContact(){
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(20))
                    .withAddress(CommonFunctions.randomString(30))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withLastName("modified LastName");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canAddContactInGroup() {
        GroupData group;
        ContactData contact;
        //создание группы для теста на контакт
            app.groups().createGroup(new GroupData("", "contact group", "new header", "new footer"));
        group = app.hbm().getGroupList().get((int)app.hbm().getGroupCount()-1);
        var oldRelated =  app.hbm().getContactsInGroup(group);
        // создание контакта, если контакт отсутствует
        if (app.hbm().getContactCount() == 0) {
            contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(15))
                    .withLastName(CommonFunctions.randomString(20))
                    .withAddress(CommonFunctions.randomString(30))
                    .withPhoto(randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);
            contact = app.hbm().getContactList().get(0);
            app.contacts().addContactToGroup(group,contact);
        } else {
            contact = app.hbm().getContactList().get(0);
            app.contacts().addContactToGroup(group,contact);
        }
        var newRelated =  app.hbm().getContactsInGroup(group);
        var uiRelated = app.contacts().getGroupContacts(group);
        boolean result = compareGroupContactLists(newRelated,uiRelated);
        // проверка, что контакт добавлен
        Assertions.assertEquals(oldRelated.size()+1, newRelated.size());
        // проверка что список контактов у группы в ui и получаемый из бд идентичны после добавления контакта в группу
        Assertions.assertTrue(result);
    }
}


