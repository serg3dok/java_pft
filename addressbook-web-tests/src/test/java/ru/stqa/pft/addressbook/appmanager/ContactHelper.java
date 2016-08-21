package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void sendContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactCreationForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());

        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFaxPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        //Birthday
        click(By.xpath("//div[@id='content']/form/select[1]//option[1]"));
        click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
        click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
        type(By.name("byear"), contactData.getYear());
        click(By.xpath("//div[@id='content']/form/select[3]//option[3]"));
        click(By.xpath("//div[@id='content']/form/select[4]//option[5]"));
        type(By.name("ayear"), contactData.getAnYear());
        //click(By.xpath("//div[@id='content']/form/select[5]//option[2]"));
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getHome2());
        type(By.name("notes"), contactData.getNotes());

/*        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else  {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }*/

    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectFirstContact()  {
        click(By.xpath("//*[@type='checkbox']"));

    }

    public void selectContact(int record)  {
        click(By.xpath("//tr[" + record + "]/td[1]"));

    }

    public void deleteContact()  {
        click(By.xpath("//form[2]/div[2]/input"));
        alertAccept();

    }

    public void initEditContact() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

    }

    public void initEditContact(int record) {
        wd.findElements(By.xpath("//td[8]/a/img")).get(record).click();
    }

    public void clickUpdateContactButton() {
        click(By.name("update"));
    }


    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]"));
        //return isElementPresent(By.name("entry"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactCreationForm(contactData, true);
        sendContactForm();


    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));

        System.out.println(rows.size());
        int i = 2;
        for (WebElement row : rows) {
            int id = Integer.parseInt(row.findElement(By.name("selected[]")).getAttribute("id")); // id
            String lastname = row.findElement(By.xpath("//*[@id='maintable']/tbody/tr[" + i +"]/td[2]")).getText(); // last name
            String firstname = row.findElement(By.xpath("//*[@id='maintable']/tbody/tr[" + i +"]/td[3]")).getText(); // first name
            String address = row.findElement(By.xpath("//*[@id='maintable']/tbody/tr[" + i +"]/td[4]")).getText();  // address

            /*String email1 = wd.findElement(By.xpath("/*//*[@id='maintable']/tbody/tr[" + i +"]/td[5]/a[1]")).getText();
            String email2 = wd.findElement(By.xpath("/*//*[@id='maintable']/tbody/tr[" + i +"]/td[5]/a[2]")).getText();
            String email3 = wd.findElement(By.xpath("/*//*[@id='maintable']/tbody/tr[" + i +"]/td[5]/a[3]")).getText();*/

            ContactData contact = new ContactData( id,  firstname, null, lastname, null, null, null, address, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null );
            contacts.add(contact);
            System.out.println("i = " + i);
            i++;
        }

        return contacts;
    }


    public int lastRowById(List<ContactData> before) {
        int id = 0;
        // find last id
        for (int i = 0; i < before.size(); i++) {
            if (id < before.get(i).getId()) id = before.get(i).getId();
        }
        int row = 0;
        // find row of id
        for (int i = 0; i < before.size(); i++) {
            if (before.get(i).getId() == id) {
                break;
            }
            else {
                row++;
            }
        }
        return row;
    }


}
