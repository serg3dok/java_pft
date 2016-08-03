package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by IEUser on 7/31/2016.
 */
public class NavigationHelper extends HelperBase{
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        super (wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
