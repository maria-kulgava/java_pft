package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
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
  private WebDriver driver;

  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private NavigationHelper navigationHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties(); // инициализация объекта properties
  }

  public void init() throws IOException {
    // Загрузка свойств, которые загружаются из конфигурационного файла
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if(driver != null){
      driver.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if(registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public NavigationHelper navigation() {
    if(navigationHelper == null){
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }

  public FtpHelper ftp() {
    if(ftp == null){
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if(mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public WebDriver getDriver() {
    // Ленивая инициализация драйвкера
    if (driver == null) {
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        driver = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        driver = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.IE)) {
        driver = new InternetExplorerDriver();
      }
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.get(properties.getProperty("web.baseUrl"));  // url-адрес берется из конфигурационного файла local.properties
    }
    return driver;
  }
}
