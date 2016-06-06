package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Admin on 6/2/2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("Nina", "Test", "3333333333", "nina.test@mail.ru", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ira", "Test", "2222222222", "ira.test@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
  }

}
