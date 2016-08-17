package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{


    @Test
    public void ContactCreationTests() {
        app.getNavigationHelper().gotoHomePage();
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();

        ContactData newContact = new ContactData("username",
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
                "asassa");
        app.getContactHelper().createContact(newContact);
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());


        int id = 0;
        for (int i = 0; i < after.size(); i++) {
            if (id < after.get(i).getId()) id = after.get(i).getId();
        }

        newContact.setId(id);
        before.add(newContact);

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
