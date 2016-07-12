package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 7/11/2016.
 */
public class ContactDeleteFromGroupTests extends TestBase {

  private GroupData selectedGroup = null;
  private ContactData selectedContact = null;

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if (contacts.size() == 0) {
      if (groups.size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test 1"));
        groups = app.db().groups();
      }
      app.contact().create(new ContactData()
              .setFirstname("Anna").setLastname("Test").setHomePhone("111").setMobilePhone("222").setWorkPhone("333")
              .setEmail("inga.test@mail.ru").setPhoto(new File("src/test/resources/smil1.png")).inGroup(groups.iterator().next()));
      contacts = app.db().contacts();
    }

    selectedGroup = groups.iterator().next();
    System.out.println("selectedGroup: " + selectedGroup.getId() + " " + selectedGroup.getName());
    Contacts groupContacts = selectedGroup.getContacts();
    System.out.println("GroupContacts: " + groupContacts);

    for(ContactData contact : contacts){
      while (groupContacts.contains(contact)){
        selectedContact = contact;
        System.out.println("selectedContact1: " + selectedContact.getId());
        break;
      }
    }

    if (selectedContact == null) {
      selectedContact = contacts.iterator().next();
      System.out.println("selectedContact2: " + selectedContact.getId());
      app.goTo().homePage();
      app.contact().addContactToGroup(selectedContact, selectedGroup);
    }

  }

  @Test
  public void testContactDeleteFromGroup() {
    Contacts beforeGroupContacts = selectedGroup.getContacts();

    app.goTo().homePage();
    app.contact().deleteContactFromGroup(selectedContact, selectedGroup);

    Groups groups = app.db().groups();
    selectedGroup = groups.iterator().next();
    Contacts afterGroupContacts = selectedGroup.getContacts();
    System.out.println("afterGroupContacts: " + afterGroupContacts);

    assertThat(afterGroupContacts, equalTo(beforeGroupContacts.without(selectedContact)));
  }
}
