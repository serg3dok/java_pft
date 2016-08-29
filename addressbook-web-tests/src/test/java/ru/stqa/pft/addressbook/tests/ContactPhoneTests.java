package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.mustache.StringChunk;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{


    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        //Contacts before = app.contacts().all();

        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoEditForm = app.contacts().infoFromEditForm(contact);



        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone) {

        String result = phone.replaceAll("\\s", "").replace("(0", "").replaceAll("[-()]", "");
        return result;
    }
}
