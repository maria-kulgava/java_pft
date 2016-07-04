package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Admin on 6/1/2016.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class); // добавление логгера (пакет org.slf4j) для указанного класса

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite (alwaysRun = true) // опция (alwaysRun = true) - что бы браузер останавливался всегда
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = true) // опция (alwaysRun = true) - чтобы метод отрабатывал всегда, даже если тест падает
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

}
