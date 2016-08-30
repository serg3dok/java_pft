package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IEUser on 7/31/2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();

        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstName("username").withMiddlename("middlename").withLastname("lastname").withNickname("nickname").withTitle("title").
                    withCompany("company1").withAddress("address").withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone").
                    withEmail("email").withEmail2("email2").withEmail3("email3").withHomepage("homepage").withBirthdayYear("1980").withAnYear("2000").withAddress2("address2").
                    withNotes("notes"));
        }
    }

    @Test
    public void testContactDeletion () {

        app.goTo().homePage();

        Contacts before = app.contacts().all();
        //System.out.println(before.size());

        ContactData deletedContact = before.iterator().next();
        //app.contacts().select(before.size() +1);
        app.contacts().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.count(), equalTo(before.size()-1));
        Contacts after = app.contacts().all();
        //System.out.println(after.size());


        assertThat(after, equalTo(before.without(deletedContact)));

    }
}
