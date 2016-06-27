package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

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
    File photo = new File("src/test/resources/smil1.png");
    ContactData contact = new ContactData()
            .setFirstname("Inga").setLastname("Test").setHomePhone("111").setMobilePhone("222").setWorkPhone("333").setEmail("inga.test@mail.ru").setGroup("test1").setPhoto(photo);
    app.contact().create(contact);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after,
            equalTo(before.withAdded(contact.setId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

  }

// Вспомогательный тест для определения абсолютного пути к файлу
//  @Test
//  public void currentDir(){
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/smil1.jpg");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

}
