package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.goTo().gotoHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("username",
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


        app.goTo().gotoHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        //app.getContactHelper().selectFirstContact();
        int id = 0;
        for (int i = 0; i < before.size(); i++) {
            if (id < before.get(i).getId()) id = before.get(i).getId();
        }
        int row = 0;
        for (int i = 0; i < before.size(); i++) {
            if (before.get(i).getId() == id) {
                break;
            }
            else {
                row++;
            }
        }

        app.getContactHelper().initEditContact(row);
        //System.out.println(before.get(before.size() - 1).getId());

        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "John",
                "Mc",
                "Smith",
                "JS",
                "CEO",
                "Corp",
                "MTW",
                "555",
                "123",
                "232323",
                "12312",
                "sasa@sds",
                "sasa",
                "sdsds",
                "sdsdspage",
                "1990",
                "1988",
                "",
                "",
                "",
                "");

        app.getContactHelper().fillContactCreationForm(contact, false);
        app.getContactHelper().clickUpdateContactButton();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(),after.size());



        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        before.sort(byId);
        after.sort(byId);

        before.remove(before.size() -1);
        before.add(contact);

        Assert.assertEquals(before, after);
    }
}
