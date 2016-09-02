package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastname(split[1]).withAddress(split[2]) } );
            line = reader.readLine();

        }
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void ContactCreationTests(ContactData contact) {


        app.goTo().homePage();
        Contacts before = app.contacts().all();
        app.contacts().initCreation();

        File photo = new File("src/test/resources/index.jpg").getAbsoluteFile();

        /*ContactData newContact = new ContactData().withFirstName("username").withMiddlename("middlename").withLastname("lastname").withNickname("nickname").withTitle("title").
                withCompany("company1").withAddress("address").withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone").
                withEmail("email").withEmail2("email2").withEmail3("email3").withHomepage("homepage").withBirthdayYear("1980").withAnYear("2000").withAddress2("address2").
                withNotes("notes").withPhoto(photo);*/
        app.contacts().create(contact);
        app.goTo().homePage();

        assertThat(app.count(), equalTo(before.size()+1));

        Contacts after = app.contacts().all();
        //Assert.assertEquals(after.size(), before.size() +1);
        //before.add(newContact);
        //assertThat(after.size(), equalTo(before.size()+1));


        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
