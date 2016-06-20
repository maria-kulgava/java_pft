package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Admin on 5/31/2016.
 */
public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    Contacts before = app.contact().all();
    ContactData contact = new ContactData("Inga", "Test", "1111111111", "anna.test@mail.ru", "test1");
    app.contact().create(contact);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after,
            equalTo(before.withAdded(contact.setId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

  }

}
