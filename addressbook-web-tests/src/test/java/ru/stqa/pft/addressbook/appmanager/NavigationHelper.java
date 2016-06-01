package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Admin on 6/1/2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver driver) {
    super(driver);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}
