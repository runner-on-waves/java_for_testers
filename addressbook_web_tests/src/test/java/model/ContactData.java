package model;

public record ContactData(String firstName, String lastName, String nickname,
                          String company, String address, String mobilePhone, String email2,
                          String email3,
                          String birthday, String year) {



    public ContactData() {

        this("", "", "", "","", "", "", "", "", "" );
    }


    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName, this.lastName, this.nickname, this.company, this.address, this.mobilePhone, this.email2,this.email3, this.birthday, this.year);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.firstName,lastName, this.nickname, this.company, this.address, this.mobilePhone, this.email2,this.email3, this.birthday, this.year);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.firstName,this.lastName, this.nickname, this.company, address, this.mobilePhone, this.email2,this.email3, this.birthday, this.year);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.firstName,this.lastName, this.nickname, this.company, address, this.mobilePhone, email2,this.email3, this.birthday, this.year);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.firstName,this.lastName, this.nickname, this.company, address, this.mobilePhone, this.email2,email3, this.birthday, this.year);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.firstName,this.lastName, this.nickname, this.company, address, mobilePhone, this.email2,this.email3, this.birthday, this.year);
    }
    public ContactData withYearOfBirth(String year) {
        return new ContactData(this.firstName,this.lastName, this.nickname, this.company, address, mobilePhone, this.email2,this.email3, this.birthday, year);
    }

}

