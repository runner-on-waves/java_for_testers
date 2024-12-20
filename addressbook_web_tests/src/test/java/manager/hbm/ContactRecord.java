package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    public String middlename = "";
    public String nickname;
    public String company;
    public String home;
    public String work;
    public int domain_id = 0;
    public String email;
    public String email2;
    public String email3;
    public String fax = "";
    public String homepage = "";
    public String mobile;
    public String title = "";
    @Column(name = "phone2")
    public String secondary = "";

    public ContactRecord() {

    }

    public ContactRecord(int id, String firstname, String lastname, String address, String nickname, String mobile, String email, String company, String work, String home, String secondary, String email2, String email3) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.nickname = nickname;
        this.mobile = mobile;
        this.work = work;
        this.home = home;
        this.email = email;
        this.company = company;
        this.email2 = email2;
        this.email3 = email3;
    }
}

