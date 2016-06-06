package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Admin on 6/2/2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test4"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }


}
