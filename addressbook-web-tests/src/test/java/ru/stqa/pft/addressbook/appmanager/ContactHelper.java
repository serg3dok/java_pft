package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void sendContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillForm(ContactData contactData, boolean creation) {

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

    public void initCreation() {
        click(By.linkText("add new"));
    }

    public void selectFirst()  {
        click(By.xpath("//*[@type='checkbox']"));

    }

    public void select(int record)  {
        click(By.xpath("//tr[" + record + "]/td[1]"));

    }

    public void deletePop()  {

        alertAccept();

    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        deletePop();
        contactCache = null;

    }

    private void deleteSelectedContact() {
        click(By.xpath("//form[2]/div[2]/input"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]"));
        //return isElementPresent(By.name("entry"));
    }

    public void create(ContactData contactData) {
        initCreation();
        fillForm(contactData, true);
        sendContactForm();
        contactCache = null;


    }

    public int count() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));

        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));

            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();

            //String[] emails = cells.get(4).getText().split("\n");
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastname(lastname)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails));

        }
        return new Contacts(contactCache);
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

    public void modify( ContactData contact) {
        initContactModificationById(contact.getId());
        fillForm(contact, false);
        submitContactModification();
        contactCache = null;

    }



    public void initEdit(int record) {
        wd.findElements(By.xpath("//td[8]/a/img")).get(record).click();
    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());

        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");


        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname)
                .withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    public ContactData infoFromContactDetailsPage(ContactData contact) {
        clickOnContactDetailsById(contact.getId());

        String contactDetailsRaw = wd.findElement(By.xpath("//*[@id='content']")).getText();


        String firstname = contactDetailsRaw.split(" ")[0];
        //String middlename = firstMiddleLastNames.split(" ")[1];
        String lastname = contactDetailsRaw.split(" ")[2].split("\\n")[0];
        String address = contactDetailsRaw.split("\\n")[4];

        String phone = "";
        String homePhone = "";
        String mobilePhone = "";
        String workPhone = "";

        phone = contactDetailsRaw.split("\\n")[6];
        if (phone.startsWith("H")) {
            homePhone = phone.replace("H: ", "");
        } else if (phone.startsWith("M")) {
            mobilePhone = phone.replace("M: ", "");
        } else if (phone.startsWith("W")) {
            workPhone = phone.replace("W: ", "");
        }

        phone = contactDetailsRaw.split("\\n")[7];
        if (phone.startsWith("H")) {
            homePhone = phone.replace("H: ", "");
        } else if (phone.startsWith("M")) {
            mobilePhone = phone.replace("M: ", "");
        } else if (phone.startsWith("W")) {
            workPhone = phone.replace("W: ", "");
        }

        phone = contactDetailsRaw.split("\\n")[8];
        if (phone.startsWith("H")) {
            homePhone = phone.replace("H: ", "");
        } else if (phone.startsWith("M")) {
            mobilePhone = phone.replace("M: ", "");
        } else if (phone.startsWith("W")) {
            workPhone = phone.replace("W: ", "");
        }

        String email1 = wd.findElement(By.xpath("//*[@id='content']/a[1]")).getText();
        String email2 = wd.findElement(By.xpath("//*[@id='content']/a[3]")).getText();
        String email3 = wd.findElement(By.xpath("//*[@id='content']/a[5]")).getText();


        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastname(lastname).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email1).withEmail2(email2).withEmail3(email3);

    }


    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
        //         edit.php?id=
    }

    public void clickOnContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();

    }

    public void initEditContact() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

    }

}
