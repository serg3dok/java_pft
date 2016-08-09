package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void ContactCreationTests() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().createContact(new ContactData("username",
                "middlename",
                "lastname",
                "nickname",
                "title",
                "company",
                "address",
                "homePhone",
                "mobilePhone",
                "workPhone",
                "faxPhone",
                "email",
                "email2",
                "email3",
                "homepage",
                "1980",
                "2000",
                "address2",
                "home2",
                "notes",
                "test1"));
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }


}
