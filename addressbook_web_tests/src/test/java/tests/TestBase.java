package tests;

import manager.ApplicationManager;
import model.ContactData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        var properties = new Properties();
        properties.load(new FileReader(System.getProperty("target", "local.properties")));
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"), properties);

    }

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }


    @AfterEach
    void  checkDatabaseConsistency(){
        app.jdbc().checkConsistency();
    }
    public boolean compareGroupContactLists(List<ContactData> hbmContacts, List<ContactData> uiContacts) {
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        hbmContacts.sort(compareById);
        var hbmForComparison = new ArrayList<ContactData>();
        for (var contact : hbmContacts) {
            hbmForComparison.add(new ContactData()
                    .withId(contact.id()).
                    withFirstName(contact.firstName())
                    .withLastName(contact.lastName())
                    .withEmail(contact.email())
                    .withCompany(contact.company())
                    .withAddress(contact.address())
                    .withMobilePhone(contact.mobilePhone())
                    .withNickName(contact.nickname())
                    .withPhoto(contact.photo())
            );
        }
        uiContacts.sort(compareById);
        return uiContacts.equals(hbmContacts);
    }
}
