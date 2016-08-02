package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactHelper extends HelperBase {

    FirefoxDriver wd;

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void sendContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactCreationForm(ContactData contactData) {

        type(By.name("firstname"), contactData.getUsername());
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
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectFirstContact()  {
        click(By.xpath("//*[@type='checkbox']"));

    }

    public void deleteContact()  {
        click(By.xpath("//form[2]/div[2]/input"));
        alertAccept();

    }

    public void editContact() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

    }

    public void clickUpdateContactButton() {
        click(By.name("update"));
    }


}
