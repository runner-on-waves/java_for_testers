package manager;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

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


    public void createContact(ContactData contact, GroupData group) {
        openContactPage();
        fillContactForm(contact);
        selectGroup(group);
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
        if ("".equals(contact.photo())) {
            attach(By.name("photo"), "src/test/resources/images/avatar.png");
        } else attach(By.name("photo"), contact.photo());
    }

    public void openContactPage() {
        click(By.linkText("add new"));
    }

    public void removeContact(ContactData contact) {
        returnToHomePage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        returnToHomePage();
        selectGroupForRemoval(group);
        selectContact(contact);
        removeSelectedContactFromGroup();
        returnToHomePage();
    }

    private void removeSelectedContactFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    //
    public void removeAllContacts() {
        returnToHomePage();
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectAllContacts() {
        click(By.xpath("//input[@id=\'MassCB\']"));
    }

    private void removeSelectedContact() {
        {
            click(By.xpath("//input[@value=\'Delete\']"));
        }
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value = '%s']", contact.id())));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupWithContacts(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectGroupForRemoval(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public int getCount() {
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.name("entry"));
        for (var entry : entries) {
            var cells = entry.findElements(By.tagName("td"));
            var lastName = cells.get(1).getText();
            var firstName = cells.get(2).getText();
            var address = cells.get(3).getText();
            var checkbox = entry.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName).withAddress(address));

        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData testData) {
        returnToHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(testData);
        submitContactModification();
        returnToHomePage();
    }

    private void submitContactModification() {
        click(By.xpath("//input[@name='update']"));
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    public void addContactToGroup(GroupData group, ContactData contact) {
        returnToHomePage();
        selectContact(contact);
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        click(By.xpath("//input[@name='add']"));
        returnToHomePage();
    }

    public List<ContactData> getGroupContacts(GroupData group) {
        returnToHomePage();
        selectGroupWithContacts(group);
        return getList();
    }

    public String getPhones(ContactData contact) {
       return manager.driver.findElement(By.xpath(
                String.format("//input[@id = '%s']/../../td[6]", contact.id()))).getText();
    }
}