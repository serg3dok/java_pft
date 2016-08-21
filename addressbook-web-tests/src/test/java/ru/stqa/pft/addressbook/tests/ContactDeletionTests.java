package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by IEUser on 7/31/2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {

        app.goTo().gotoHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("username",
                    "Rober",
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
        app.goTo().gotoHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() +1);
        app.getContactHelper().deleteContact();
        app.goTo().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size() -1, after.size());

        before.remove(before.size() -1);

        Assert.assertEquals(before, after);

        //Assert.assertEquals(before, after);
    }
}
