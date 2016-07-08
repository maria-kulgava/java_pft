package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Admin on 5/31/2016.
 */
public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null){
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>)xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact){
    Contacts before = app.db().contacts();
//    File photo = new File("src/test/resources/smil1.png");
//    ContactData contact = new ContactData()
//            .setFirstname("Inga").setLastname("Test").setHomePhone("111").setMobilePhone("222").setWorkPhone("333").setEmail("inga.test@mail.ru").setGroup("test1").setPhoto(photo);
    app.contact().create(contact);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after,
            equalTo(before.withAdded(contact.setId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

  }

}
