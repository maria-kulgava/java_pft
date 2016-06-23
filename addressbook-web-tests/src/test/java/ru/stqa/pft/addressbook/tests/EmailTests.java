package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 6/22/2016.
 */
public class EmailTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .setFirstname("Nina").setLastname("Test").setMobilePhone("111-11-11").setEmail("nina.test@mail.ru").setGroup("test1"));
    }
  }

  @Test
  public void testAddress(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().contactInfoFormEditForm(contact);

    assertThat(cleaned(contact.getEmail()), equalTo(cleaned(contactInfoFormEditForm.getEmail())));
  }

  public static String cleaned(String email){
    return email.replaceAll("\\s", "");
  }
}
