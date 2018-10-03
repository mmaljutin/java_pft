package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
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
        ContactData contact = new ContactData(before.get(before.size() -1).getId(), "firstnameTest", "lastnameTest", "addressTest", "1234567890", "test@test.ee", null);
        app.getContactHelper().updateContact(contact);
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
