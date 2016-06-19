package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Admin on 5/31/2016.
 */
public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData("Inga", "Test", "1111111111", "anna.test@mail.ru", "test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

  }

}
