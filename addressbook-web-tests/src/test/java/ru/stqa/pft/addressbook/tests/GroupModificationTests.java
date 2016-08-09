package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupdCount();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));

        }
        app.getGroupHelper().selectGroup(before  - 1 );
        app.getGroupHelper().initGroupMOdification();
        app.getGroupHelper().fillGroupForm(new GroupData("asassa", "sasasa", null));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupdCount();
        Assert.assertEquals(before, after);
    }
}
