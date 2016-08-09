package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
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
                    "notes blah blah blah",
                    "test1"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().editContact();

        app.getContactHelper().fillContactCreationForm(new ContactData("John", "Mc", "Smith", "JS", "CEO", "Corp", "MTW", "555", "123", "232323", "12312", "sasa@sds", "sasa",
                "sdsds", "sdsdspage", "1990", "1988", "", "", "", null), false);
        app.getContactHelper().clickUpdateContactButton();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before, after);
    }
}
