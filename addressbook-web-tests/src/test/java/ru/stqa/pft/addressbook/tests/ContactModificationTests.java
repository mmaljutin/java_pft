package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectedContact();
        app.getContactHelper().fillContactPage(new ContactData("firstnameTest", "lastnameTest", "addressTest", "1234567890", "test@test.ee", null), false);
        app.getContactHelper().submitContactModification();
    }
}
