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
 * Created by Admin on 7/10/2016.
 */
public class ContactAddToGroupTests extends TestBase {

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

    selectedContact = contacts.iterator().next();
    System.out.println("selectedContact: " + selectedContact.getId());
    Groups contactGroups = selectedContact.getGroups();
    System.out.println("contactGroups: " + contactGroups);

    for (GroupData group : groups) {
      while (!contactGroups.contains(group)) {
        selectedGroup = group;
        System.out.println("selectedGroup1: " + selectedGroup.getId());
        break;
      }
    }

    if (selectedGroup == null) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 778"));
      groups = app.db().groups();
      for (GroupData group : groups) {
        if (group.getName().equals("test 778")) {
          selectedGroup = group;
          System.out.println("selectedGroup2: " + selectedGroup.getId() + " " + selectedGroup.getName());
        }
      }
    }

  }


  @Test
  public void testContactAddToGroup() {
    Groups beforeContactGroups = selectedContact.getGroups();
    System.out.println("beforeContactGroups: " + beforeContactGroups);

    app.goTo().homePage();
    app.contact().addContactToGroup(selectedContact, selectedGroup);
    app.goTo().homePage();


    Contacts contacts = app.db().contacts();
    selectedContact = contacts.iterator().next();
    Groups afterContactGroups = selectedContact.getGroups();
    System.out.println("afterContactGroups: " + afterContactGroups);

    assertThat(afterContactGroups, equalTo(beforeContactGroups.withAdded(selectedGroup)));

  }

}
