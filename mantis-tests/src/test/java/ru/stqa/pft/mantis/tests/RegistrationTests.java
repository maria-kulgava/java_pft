package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Admin on 7/16/2016.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration(){
    app.registration().start("user1", "user1@lockalhost.lockaldomain");
  }
}
