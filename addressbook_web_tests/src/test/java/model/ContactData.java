package model;

public record ContactData(String firstName, String middleName, String lastName, String nickname,
                          String company, String address, String homePhone, String mobilePhone, String workPhone,
                          String fax, String email, String email2,
                          String email3,
                          String birthday,String monthOfBirth, String year) {


    public ContactData() {

        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "","");
    }


    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday, this.monthOfBirth, this.year);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.firstName, middleName, this.lastName, this.nickname, company, address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, year);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.firstName, this.middleName, lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withNickName(String nickName) {
        return new ContactData(this.firstName, this.middleName, lastName, nickName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, email, email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withAllEmails(String email, String email2, String email3) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, email, email2, email3, this.birthday,this.monthOfBirth, year);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, homePhone, mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, homePhone, mobilePhone, workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withFax(String fax) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, homePhone, mobilePhone, workPhone, fax, this.email, this.email2, this.email3, this.birthday, this.monthOfBirth,this.year);
    }

    public ContactData withAllPhones(String homePhone, String mobilePhone, String workPhone) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, homePhone, mobilePhone, workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, this.year);
    }

    public ContactData withYearOfBirth(String year) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, year);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,this.monthOfBirth, year);
    }
    public ContactData withMonthOfBirth(String monthOfBirth) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickname, company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3, this.birthday,monthOfBirth, year);
    }

}

