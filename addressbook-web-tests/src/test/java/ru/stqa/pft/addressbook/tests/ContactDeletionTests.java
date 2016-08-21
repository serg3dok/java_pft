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
            app.contacts().create(new ContactData("username",
                    "Robert",
                    "Radrigez",
                    "Jora",
                    "facility worker",
                    "Robots and parts",
                    "Mountain View, California",
                    "123156489",
                    "54984615",
                    "65168465156",
                    "56146846",
                    "esdasdasd@mail",
                    "email2@sdfsdfs",
                    "email3@sdfsdfs",
                    "dasda.sadas.com",
                    "1960",
                    "1900",
                    "addresddasdss2",
                    "home2",
                    "notes blah blah blah"));
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
