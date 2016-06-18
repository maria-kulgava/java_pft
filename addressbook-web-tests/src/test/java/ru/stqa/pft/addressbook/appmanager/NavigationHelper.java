package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Admin on 6/1/2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1")) && driver.findElement(By.tagName("h1")).getText().equals("Groups") && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void homePage() {
    if (isElementPresent(By.id("mailtable"))){
      return;
    }
    click(By.linkText("home"));
  }
}
