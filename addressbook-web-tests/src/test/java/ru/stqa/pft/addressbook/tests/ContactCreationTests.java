package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        Contacts before = app.contact().all();
        app.goTo().contactPage();
        File photo = new File("src/test/resources/tom.png");
        ContactData contact = new ContactData()
                .withFirstname("firstnameTest")
                .withLastname("lastnameTest")
                .withAddress("P.O. Box 283 8562 Fusce Rd, Frederick Nebraska 20620")
                .withPhone("1234567890")
                .withMobilePhone("111")
                .withWorkPhone("222")
                .withEmail("test@test.ee")
                .withEmail2("test@test.ru")
                .withEmail3("test@test.com")
                .withGroup("[none]")
                .withPhoto(photo);
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
                .withFirstname("BadfirstnameTest'")
                .withLastname("BadlastnameTest")
                .withAddress("P.O. Box 283 8562 Fusce Rd, Frederick Nebraska 20620")
                .withPhone("1234567890")
                .withMobilePhone("111")
                .withWorkPhone("222")
                .withEmail("test@test.ee")
                .withEmail2("test@test.ru")
                .withEmail3("test@test.com")
                .withGroup("[none]");
        app.contact().create(contact);
        app.goTo().returnToHomePage();

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }


}
