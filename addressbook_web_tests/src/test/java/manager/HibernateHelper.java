package manager;

import io.qameta.allure.Step;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        .addAnnotatedClass(GroupRecord.class)
                        .addAnnotatedClass(ContactRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        // Credentials
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    @Step
    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));

    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery(" select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();

        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();

        });
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId("" + record.id)
                .withFirstName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address)
                .withNickName(record.nickname)
                .withEmail(record.email)
                .withMobilePhone(record.mobile)
                .withWorkPhone(record.work)
                .withHomePhone(record.home)
                .withSecondary(record.secondary)
                .withCompany(record.company)
                .withEmail2(record.email2)
                .withEmail3(record.email3);
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.address(), data.nickname(), data.mobilePhone(), data.email(), data.company(), data.workPhone(), data.secondary(), data.homePhone(), data.email2(), data.email3());
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));

    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery(" select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
