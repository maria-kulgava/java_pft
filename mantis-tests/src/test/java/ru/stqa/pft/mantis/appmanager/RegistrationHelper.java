package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Admin on 7/16/2016.
 */
public class RegistrationHelper extends HelperBase {

   public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    driver.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    driver.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void loginAsAdmin(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void resetPassword(String username) {
    selectUser(username);
    click(By.cssSelector("input[value='Reset Password']"));
  }

  private void selectUser(String username) {
    click(By.xpath("//a[text()='" + username + "']"));
  }


}
