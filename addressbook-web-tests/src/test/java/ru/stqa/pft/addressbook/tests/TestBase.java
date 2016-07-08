package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

  public void verifyGroupListInUI() {
    // Реализуем регулировку запуска проверки из конфигурации
    if(Boolean.getBoolean("verifyGroupUI")){  // получаем системное свойство с заданным именем и преобразуем его в булевую велечину
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream().map((g)-> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if(Boolean.getBoolean("verifyContactUI")){  // получаем системное свойство с заданным именем и преобразуем его в булевую велечину
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream().map((g)-> new ContactData().setId(g.getId()).setFirstname(g.getFirstname()).setLastname(g.getLastname())
              .setAddress(g.getAddress()).setAllEmails(g.getAllEmails()).setAllPhones(g.getAllPhones()))
              .collect(Collectors.toSet())));
    }
  }


}
