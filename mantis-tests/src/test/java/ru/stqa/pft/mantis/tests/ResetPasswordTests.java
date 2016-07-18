package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Admin on 7/17/2016.
 */
public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException {
    String userName = "Maria";
    String newPassword = "newPassword";
    String email = "maria@mail.ru";

    // Администратор входит в систему, переходит на страницу управления пользователями, выбирает заданного пользователя и нажимает кнопку Reset Password
    app.getDriver();
    app.registration().loginAsAdmin("administrator", "root");
    app.navigation().openManageUsersPage();
    app.registration().resetPassword(userName);

    // Отправляется письмо на адрес пользователя, получаем это письмо, извлекаем из него ссылку для смены пароля, переходим по этой ссылке и изменяем пароль.
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, newPassword);

    // проверztv, что пользователь может войти в систему с новым паролем.
    assertTrue(app.newSession().login(userName, newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
