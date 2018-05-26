package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumHQTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.seleniumhq.org/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSeleniumHQ() throws Exception {
    // First Test Case
    // open | / | 
    driver.get(baseUrl + "/");
    // assertTitle | Selenium - Web Browser Automation | 
    assertEquals("Selenium - Web Browser Automation", driver.getTitle());
    // click | link=Selenium IDE | 
    driver.findElement(By.linkText("Selenium IDE")).click();
    // click | link=Selenium IDE Plugins* | 
    driver.findElement(By.linkText("Selenium IDE Plugins*")).click();
    // assertTitle | Selenium IDE | 
    assertEquals("Selenium IDE", driver.getTitle());
    // verifyText | //div[@id='mainContent']/h3[2] | Hosting
    try {
      assertEquals("Hosting", driver.findElement(By.xpath("//div[@id='mainContent']/h3[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
