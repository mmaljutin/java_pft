package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("firstnameTest", "lastnameTest", "addressTest", "1234567890", "test@test.ee", "[none]"));
        }
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().editSelectedContact();
        app.getContactHelper().updateContact(new ContactData("firstnameTest", "lastnameTest", "addressTest", "1234567890", "test@test.ee", null));
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        // 4.7 03:00
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }
}
