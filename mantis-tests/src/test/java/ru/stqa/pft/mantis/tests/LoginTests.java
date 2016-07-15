package ru.stqa.pft.mantis.tests;

import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Admin on 7/15/2016.
 */
public class LoginTests extends TestBase {

  @Test
  public void testLigin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
