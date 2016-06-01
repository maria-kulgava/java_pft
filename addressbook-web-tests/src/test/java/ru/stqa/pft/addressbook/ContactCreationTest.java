package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

/**
 * Created by Admin on 5/31/2016.
 */
public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation(){
    initContactCreation();
    fillContactForm(new ContactData("Anna", "Test", "1111111111", "anna.test@mail.ru"));
    submitContactCreation();
  }


}
