package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactPage(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }
    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void editSelectedContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }
}
