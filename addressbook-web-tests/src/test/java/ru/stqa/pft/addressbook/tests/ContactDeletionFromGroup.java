package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;

public class ContactDeletionFromGroup extends TestBase {
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
    public void removeContactFromGroupTests() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups allGroups = app.db().groups();
        GroupData deletedGroup = allGroups.iterator().next();
        if (!deletedGroup.equals(contact.getGroups())) {
            app.contact().addToGroup(contact, deletedGroup);
        }
        allGroups.removeAll(contact.getGroups());
        app.goTo().returnToHomePage();
        app.contact().removeFromGroup(contact, deletedGroup);
        app.db().refresh(contact);
        assertThat(contact.getGroups(), not(hasItem(deletedGroup)));
    }
}
