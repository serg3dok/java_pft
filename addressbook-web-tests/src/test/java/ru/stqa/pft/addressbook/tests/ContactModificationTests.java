package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {

        app.goTo().homePage();

        List<ContactData> before = app.contacts().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "John",
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

        app.contacts().modify(index, contact);
        app.goTo().homePage();
        List<ContactData> after = app.contacts().list();
        Assert.assertEquals(before.size(),after.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        before.sort(byId);
        after.sort(byId);


        Assert.assertEquals(before, after);
    }




}
