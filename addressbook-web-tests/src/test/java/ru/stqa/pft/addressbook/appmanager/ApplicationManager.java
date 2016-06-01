package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 6/1/2016.
 */
public class ApplicationManager {
  FirefoxDriver driver;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;

  public void init() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/group.php");
    sessionHelper = new SessionHelper(driver);
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    contactHelper = new ContactHelper(driver);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    driver.quit();
  }


  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
