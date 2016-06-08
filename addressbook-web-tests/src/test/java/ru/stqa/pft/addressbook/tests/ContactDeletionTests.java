package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Admin on 6/2/2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("Nina", "Test", "3333333333", "nina.test@mail.ru", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
  }

}
