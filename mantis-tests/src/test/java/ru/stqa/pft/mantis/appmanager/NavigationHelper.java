package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Admin on 6/1/2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void openManageUsersPage() {
    if (isElementPresent(By.cssSelector("input[value='Manage User']"))){
      return;
    }
    click(By.linkText("Manage Users"));
  }
}
