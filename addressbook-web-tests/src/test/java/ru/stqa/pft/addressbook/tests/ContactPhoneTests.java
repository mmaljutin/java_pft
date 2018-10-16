package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstname("firstnameTest")
                    .withLastname("lastnameTest")
                    .withAddress("P.O. Box 283 8562 Fusce Rd, Frederick Nebraska 20620")
                    .withPhone("+(1)234567890")
                    .withMobilePhone("1-11")
                    .withWorkPhone("22 2")
                    .withEmail("test@test.ee")
                    .withEmail2("test@test.ru")
                    .withEmail3("test@test.com")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().returnToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
}
