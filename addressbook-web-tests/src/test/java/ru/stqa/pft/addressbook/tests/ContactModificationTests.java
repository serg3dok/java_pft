package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().editContact();

        app.getContactHelper().fillContactCreationForm(new ContactData("John", "Mc", "Smith", "JS", "CEO", "Corp", "MTW", "555", "123", "232323", "12312", "sasa@sds", "sasa",
                "sdsds", "sdsdspage", "1990", "1988", "", "", ""));
        app.getContactHelper().clickUpdateContactButton();
        app.getNavigationHelper().gotoHomePage();
    }
}
