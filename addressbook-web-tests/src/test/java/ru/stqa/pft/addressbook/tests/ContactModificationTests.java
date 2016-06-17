package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 6/2/2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    if(!app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("Nina", "Test", "3333333333", "nina.test@mail.ru", "test1"));
    }
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Ira", "Test", "2222222222", "ira.test@mail.ru", null);
    app.getContactHelper().initContactModification(before.size() - 1);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
