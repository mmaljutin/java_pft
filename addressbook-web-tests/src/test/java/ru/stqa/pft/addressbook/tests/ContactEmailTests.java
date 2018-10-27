package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
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
                    .inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().returnToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }


}
