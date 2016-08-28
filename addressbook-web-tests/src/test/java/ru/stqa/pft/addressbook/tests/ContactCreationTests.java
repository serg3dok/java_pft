package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


    @Test
    public void ContactCreationTests() {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        app.contacts().initCreation();

        ContactData newContact = new ContactData().withFirstName("username").withMiddlename("middlename").withLastname("lastname").withNickname("nickname").withTitle("title").
                withCompany("company1").withAddress("address").withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone").
                withEmail("email").withEmail2("email2").withEmail3("email3").withHomepage("homepage").withBirthdayYear("1980").withAnYear("2000").withAddress2("address2").
                withHome2("home2").withNotes("notes");
        app.contacts().create(newContact);
        app.goTo().homePage();

        assertThat(app.count(), equalTo(before.size()+1));

        Contacts after = app.contacts().all();
        //Assert.assertEquals(after.size(), before.size() +1);
        //before.add(newContact);
        //assertThat(after.size(), equalTo(before.size()+1));


        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
