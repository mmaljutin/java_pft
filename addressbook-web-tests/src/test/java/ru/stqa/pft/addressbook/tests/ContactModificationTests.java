package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstname("firstnameTest")
                    .withLastname("lastnameTest")
                    .withAddress("addressTest")
                    .withPhone("1234567890")
                    .withMobilePhone("111")
                    .withWorkPhone("222")
                    .withEmail("test@test.ee")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().returnToHomePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("firstnameTestModified")
                .withLastname("lastnameTestModified")
                .withAddress("addressTestModified")
                .withPhone("1")
                .withMobilePhone("2")
                .withWorkPhone("3")
                .withEmail("Modified@test.ee");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
