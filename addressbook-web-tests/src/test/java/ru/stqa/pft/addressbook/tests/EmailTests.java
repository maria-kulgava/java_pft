package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 6/22/2016.
 */
public class EmailTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if(contacts.size() == 0){
      if(groups.size() == 0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test 1"));
        groups = app.db().groups();
      }
      app.contact().create(new ContactData()
              .setFirstname("Inga").setLastname("Test").setHomePhone("111").setMobilePhone("222").setWorkPhone("333")
              .setEmail("inga.test@mail.ru").setEmail2("inga2.test@mail.ru").setEmail3("inga3.test@mail.ru").setPhoto(new File("src/test/resources/smil1.png")).inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testAddress(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().contactInfoFormEditForm(contact);

    assertThat(cleaned(contact.getAllEmails()), equalTo(margeEmails(contactInfoFormEditForm)));
  }

  private String margeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(EmailTests::cleaned)
            .collect(Collectors.joining(""));

  }

  public static String cleaned(String email){
    return email.replaceAll("\\s", "");
  }
}
