package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

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
    public void testContactEmails() {
        app.goTo().homePage();

        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoEditForm = app.contacts().infoFromEditForm(contact);



        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoEditForm)));


    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }


    public String cleaned(String email) {
        return email;
    }
}
