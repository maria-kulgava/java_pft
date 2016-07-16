package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by Admin on 7/16/2016.
 */
public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver driver;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    driver = app.getDriver();
  }

  public void start(String username, String email) {
    driver.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
