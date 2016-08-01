package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by IEUser on 7/31/2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        app.getNavigationHelper().gotoHomePage();
        app.selectFirstContact();
        app.deleteContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
