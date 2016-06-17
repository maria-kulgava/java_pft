package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
