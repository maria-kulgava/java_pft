package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Admin on 6/1/2016.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(FirefoxDriver driver) {
    super(driver);
  }

  public void login(String username, String password) {
    type(By.name("user"), username);
    type(By.name("pass"), password);
    click(By.cssSelector("input[value=\"Login\"]"));
  }
}
