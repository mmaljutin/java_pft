package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstname("firstnameTest")
                    .withLastname("lastnameTest")
                    .withAddress("P.O. Box 283 8562 Fusce Rd, Frederick Nebraska 20620")
                    .withPhone("1234567890")
                    .withMobilePhone("111")
                    .withWorkPhone("222")
                    .withEmail("test@test.ee")
                    .withEmail2("test@test.ru")
                    .withEmail3("test@test.com")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().returnToHomePage();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
