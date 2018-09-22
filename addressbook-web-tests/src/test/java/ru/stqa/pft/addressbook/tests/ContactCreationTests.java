package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("firstnameTest", "lastnameTest", "addressTest", "1234567890", "test@test.ee", "[none]"));
        app.getNavigationHelper().returnToHomePage();
    }


}
