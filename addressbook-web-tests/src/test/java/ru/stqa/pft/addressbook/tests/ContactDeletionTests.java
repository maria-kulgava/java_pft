package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Admin on 6/2/2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
              .setFirstname("Inga").setLastname("Test").setHomePhone("111").setMobilePhone("222").setWorkPhone("333")
              .setEmail("inga.test@mail.ru").setGroup("test1").setPhoto(new File("src/test/resources/smil1.png")));
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    assertThat(app.group().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));


  }

}
