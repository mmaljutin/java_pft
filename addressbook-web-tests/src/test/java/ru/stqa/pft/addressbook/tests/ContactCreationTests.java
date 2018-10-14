package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() throws Exception {

        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
                .withFirstname("firstnameTest")
                .withLastname("lastnameTest")
                .withAddress("addressTest")
                .withPhone("1234567890")
                .withMobilePhone("111")
                .withWorkPhone("222")
                .withEmail("test@test.ee")
                .withGroup("[none]");
        app.contact().create(contact);
        app.goTo().returnToHomePage();

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() throws Exception {

        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
                .withFirstname("firstnameTest'")
                .withLastname("lastnameTest")
                .withAddress("addressTest")
                .withPhone("1234567890")
                .withEmail("test@test.ee")
                .withGroup("[none]");
        app.contact().create(contact);
        app.goTo().returnToHomePage();

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }


}
