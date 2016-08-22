package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by IEUser on 7/31/2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();

        if (app.contacts().list().size() == 0) {
            app.contacts().create(new ContactData().withFirstName("username").withMiddlename("middlename").withLastname("lastname").withNickname("nickname").withTitle("title").
                    withCompany("company1").withAddress("address").withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone").
                    withEmail("email").withEmail2("email2").withEmail3("email3").withHomepage("homepage").withBirthdayYear("1980").withAnYear("2000").withAddress2("address2").
                    withHome2("home2").withNotes("notes"));
        }
    }

    @Test
    public void testContactDeletion () {

        app.goTo().homePage();

        List<ContactData> before = app.contacts().list();
        app.contacts().select(before.size() +1);
        app.contacts().delete();
        app.goTo().homePage();

        List<ContactData> after = app.contacts().list();
        Assert.assertEquals(before.size() -1, after.size());

        before.remove(before.size() -1);

        Assert.assertEquals(before, after);

    }
}
