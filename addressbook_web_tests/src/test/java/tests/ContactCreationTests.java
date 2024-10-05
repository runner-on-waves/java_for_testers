package tests;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
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
//import static common.CommonFunctions.randomString;

public class ContactCreationTests extends TestBase {

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
                new ContactData().withAddress("Lenina'"),
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
    public void canCreateContactWithGroup() {
        app.contacts().createContactWithGroup(new ContactData());
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
                .withNickName("")
                .withMiddleName("")
                .withMobilePhone("")
                .withWorkPhone("")
                .withFax("")
                .withHomePhone("")
                .withAddress("")
                .withCompany("")
                .withEmail("")
                .withEmail2("")
                .withEmail3("")
                .withMonthOfBirth("")
                .withYearOfBirth("")
                .withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateGroups(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }
}
