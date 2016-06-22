package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 6/22/2016.
 */
public class AddressTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .setFirstname("Nina").setLastname("Test").setHomePhone("111").setMobilePhone("222").setWorkPhone("333").setAddress("123456 Minsk, ul.Nika, 3").setEmail("nina.test@mail.ru").setGroup("test1"));
    }
  }

  @Test
  public void testAddress(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().contactInfoFormEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
  }

}
