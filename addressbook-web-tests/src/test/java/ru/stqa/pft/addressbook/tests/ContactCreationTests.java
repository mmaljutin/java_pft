package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();

    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
        }.getType());  //List<ContactData>.class
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
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
