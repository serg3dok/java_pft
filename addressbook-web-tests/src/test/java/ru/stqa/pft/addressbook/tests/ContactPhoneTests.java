package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.mustache.StringChunk;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{



    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();

        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstName("username").withMiddlename("middlename").withLastname("lastname").withNickname("nickname").withTitle("title").
                    withCompany("company1").withAddress("address").withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone").
                    withEmail("email").withEmail2("email2").withEmail3("email3").withHomepage("homepage").withBirthdayYear("1980").withAnYear("2000").withAddress2("address2").
                    withNotes("notes"));
        }
    }

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
