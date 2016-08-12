package ru.stqa.pft.addressbook.model;

public class ContactData {

    private int id;
    private final String firstName;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String faxPhone;
    private final String email;
    private final String email2;
    private final String email3;
    private final String homepage;
    private final String year;
    private final String anYear;
    private final String address2;
    private final String home2;
    private final String notes;
    private String group;

    public ContactData(int id, String firstName, String middlename, String lastname, String nickname, String title, String company, String address, String homePhone, String mobilePhone, String workPhone, String faxPhone, String email, String email2, String email3, String homepage, String year, String anYear, String address2, String home2, String notes, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.faxPhone = faxPhone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.year = year;
        this.anYear = anYear;
        this.address2 = address2;
        this.home2 = home2;
        this.notes = notes;
        this.group = group;
    }



    public ContactData(String firstName, String middlename, String lastname, String nickname, String title, String company, String address, String homePhone, String mobilePhone, String workPhone, String faxPhone, String email, String email2, String email3, String homepage, String year, String anYear, String address2, String home2, String notes, String group) {
        this.id = 0;
        this.firstName = firstName;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;

        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.faxPhone = faxPhone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.year = year;
        this.anYear = anYear;
        this.address2 = address2;
        this.home2 = home2;
        this.notes = notes;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFaxPhone() {
        return faxPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getYear() {
        return year;
    }

    public String getAnYear() {
        return anYear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHome2() {
        return home2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }



}
