package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;

@XStreamAlias("contact")
public class ContactData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    private String allPhones;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    private String allEmails;
    @Expose
    private String group;
    @Expose
    private File photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.homePhone = phone;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }


    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public String getAllPhones() {
        return allPhones;
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

    public String getAllEmails() {
        return allEmails;
    }

    public String getGroup() {
        return group;
    }

    public File getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


}
