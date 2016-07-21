package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Admin on 7/16/2016.
 */
public class RegistrationTests extends TestBase {

  //@BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@lockalhost.lockaldomain", now);
    app.jemes().createUser(user, email); // создаем пользователя на почтовом сервере
    app.registration().start(user, email);
    // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); // получение почты с помощью встроенного почтового сервера
    List<MailMessage> mailMessages = app.jemes().waitForMail(user, email, 60000); // получение почты с помощью jemes
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password)); // проверяем, что пользователь может залогиниться

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //@AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
