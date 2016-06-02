package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Admin on 5/31/2016.
 */
public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Anna", "Test", "1111111111", "anna.test@mail.ru"));
    app.getContactHelper().submitContactCreation();
  }

}
