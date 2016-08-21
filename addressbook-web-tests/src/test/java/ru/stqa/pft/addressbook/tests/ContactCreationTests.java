package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{


    @Test
    public void ContactCreationTests() {
        app.goTo().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();

        ContactData newContact = new ContactData("username",
                "middlename",
                "lastname",
                "nickname",
                "title",
                "company1",
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
                "notes");
        app.getContactHelper().createContact(newContact);
        app.goTo().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);


//        int id = 0;
//        for (int i = 0; i < after.size(); i++) {
//            if (id < after.get(i).getId()) id = after.get(i).getId();
//        }
//       newContact.setId(id);
        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        before.sort(byId);
        after.sort(byId);

        for (int i = 0; i < before.size()-1; i++) {
            System.out.println("id: " + before.get(i).getId() + " | " + after.get(i).getId());
            //System.out.println("firstName: " + before.get(i).getFirstName() + "  | " + after.get(i).getFirstName());
        }

        Assert.assertEquals(before, after);
    }
}
