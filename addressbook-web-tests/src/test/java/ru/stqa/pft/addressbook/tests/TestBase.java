package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by Admin on 6/1/2016.
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeClass
  public void setUp() throws Exception {
    app.init();
  }

  @AfterClass
  public void tearDown() {
    app.stop();
  }

}
