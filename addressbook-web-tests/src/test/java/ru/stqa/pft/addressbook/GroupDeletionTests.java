package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

/**
 * Created by Admin on 6/1/2016.
 */
public class GroupDeletionTests extends TestBase  {

  @Test
  public void testGroupDeletion(){
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
