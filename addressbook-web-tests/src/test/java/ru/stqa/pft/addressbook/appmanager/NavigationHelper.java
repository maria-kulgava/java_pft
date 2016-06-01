package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Admin on 6/1/2016.
 */
public class NavigationHelper {
  private FirefoxDriver driver;

  public NavigationHelper(FirefoxDriver driver) {
    this.driver = driver;
  }

  public void gotoGroupPage() {
    driver.findElement(By.linkText("groups")).click();
  }
}
