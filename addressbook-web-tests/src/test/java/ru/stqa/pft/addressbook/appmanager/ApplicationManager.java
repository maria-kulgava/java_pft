package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 6/1/2016.
 */
public class ApplicationManager {
  private final Properties properties;
  WebDriver driver;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties(); // инициализация объекта properties
  }

  public void init() throws IOException {
    // Загрузка свойств, которые загружаются из конфигурационного файла
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if(Objects.equals(browser, BrowserType.FIREFOX)){
      driver = new FirefoxDriver();
    } else if(Objects.equals(browser, BrowserType.CHROME)){
      driver = new ChromeDriver();
    } else if(Objects.equals(browser, BrowserType.IE)) {
      driver = new InternetExplorerDriver();
    }
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));  // url-адрес берется из конфигурационного файла local.properties
    sessionHelper = new SessionHelper(driver);
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    contactHelper = new ContactHelper(driver);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword")); // логин и пароль берутся из конфигурационного файла local.properties
  }

  public void stop() {
    driver.quit();
  }


  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }
}
