package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Admin on 5/31/2016.
 */
public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation(){
    app.initContactCreation();
    app.fillContactForm(new ContactData("Anna", "Test", "1111111111", "anna.test@mail.ru"));
    app.submitContactCreation();
  }


}
