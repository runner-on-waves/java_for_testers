package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
//import static common.CommonFunctions.randomString;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> singleRandomContact() throws IOException {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30)));
    }

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        //***********4. Создание объектов из файла в формате json
        var json = Files.readString(Paths.get("contacts.json"));// чтение файла целиком
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
        //************1. Создание объектов с фиксированными значениями
//        var result = new ArrayList<>((List.of(
//                new ContactData(),
//                new ContactData().withHomePhone("+7955111111111"),
//                new ContactData().withMobilePhone("+7999111111122"),
//                new ContactData().withWorkPhone("+7999111111133"),
//                new ContactData().withFax("+7999111111144"),
//                new ContactData().withAllPhones("+7999111111111", "+7999111111122", "+7999111111133"),
//                new ContactData().withEmail("pokemon@mail.ru"),
//                new ContactData().withEmail2("pokemon2@mail.ru"),
//                new ContactData().withEmail3("pokemon3@mail.ru"),
//                new ContactData().withAllEmails("kolobok@mail.ru", "kolobok2@mail.ru", "kolobok3@mail.ru"),
//                new ContactData().withMiddleName("Petrovich"),
//                new ContactData().withNickName("Pyatachok"),
//                new ContactData().withMonthOfBirth("September"),
//                new ContactData().withYearOfBirth("2000"),
//                new ContactData().withCompany("Winnie-Pooh"),
//                new ContactData().withPhoto("src/test/resources/images/avatar.png"))));
        //**************2. Создание комбинаций значений для объектов ContactData в цикле
//       for (var firstName : List.of("", "Ivan")) {
//            for (var lastName : List.of("", "Ivanov")) {
//                for (var address : List.of("", "Lenina Street")) {
//                    for (var phone : List.of("", "+792345675690")) {
//                        for (var email : List.of("", "smile@bk.ru")) {
//                            result.add(new ContactData().withDataOnHomepage(firstName, lastName, address, phone, email));
//                        }
//                    }
//
//
//                }
//            }
//        }
        //*************3. Создание объектов с  рандомными значениями в цикле
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData("", randomString(i * 10), randomString(i * 10), randomString(i * 10),
//                    randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10),
//                    randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10),
//                    randomString(i * 10), randomString(i * 10), "", randomString(i * 10),randomFile("src/test/resources/images")));
//        }
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<>(List.of(
                new ContactData().withFirstName("Ivan'"),
                new ContactData().withMiddleName("Petrovich'"),
                new ContactData().withLastName("Ivanov'"),
                new ContactData().withNickName("Pyatachok'"),
                new ContactData().withCompany("Winnie-Pooh'"),
                new ContactData().withHomePhone("+7955111111111'"),
                new ContactData().withMobilePhone("+7999111111122'"),
                new ContactData().withWorkPhone("+7999111111133'"),
                new ContactData().withFax("+7999111111144'"),
                new ContactData().withEmail("pokemon@mail.ru'"),
                new ContactData().withEmail2("pokemon2@mail.ru'"),
                new ContactData().withEmail3("pokemon3@mail.ru'")));
        return result;
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "new name", "new header", "new footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        var extraContacts = newContacts.stream().filter(c->!oldContacts.contains(c)).toList();
        var newId = extraContacts.get(0).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newId));
        Assertions.assertEquals(Set.copyOf(newContacts),Set.copyOf(expectedList));
    }
//    public void canCreateContact(ContactData contact) {
//        var oldContacts = app.hbm().getContactList();
//        app.contacts().createContact(contact);
//        var newContacts = app.hbm().getContactList();
//        Comparator<ContactData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newContacts.sort(compareById);
//        var maxId = newContacts.get(newContacts.size() - 1).id();
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.add(contact.withId(maxId));
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newContacts, expectedList);
//    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId).withPhoto("").withSecondary(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
        //Assertions.assertTrue(compareContactList());
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        long contactCount = app.hbm().getContactCount();
        app.contacts().createContact(contact);
        long newContactCount = app.hbm().getContactCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

    @Test
    public void compareContactList() {
        var hbmContacts = app.hbm().getContactList();
        var uiContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        hbmContacts.sort(compareById);
        var hbmForComparison = new ArrayList<ContactData>();
        for (var contact : hbmContacts) {
            hbmForComparison.add(new ContactData()
                    .withId(contact.id())
                    .withFirstName(contact.firstName())
                    .withLastName(contact.lastName())
                    .withAddress(contact.address()));
        }
        uiContacts.sort(compareById);
        Assertions.assertEquals(hbmForComparison, uiContacts);
    }
}
