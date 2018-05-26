package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class FailedLoginAttempts {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.pluralsight.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testFailedLoginAttempts() throws Exception {
    // open | / | 
    driver.get(baseUrl + "/");
    // click | xpath=(//a[contains(text(),'Log in')])[2] | 
    driver.findElement(By.xpath("(//a[contains(text(),'Log in')])[2]")).click();
    // type | id=Username | invalid
    driver.findElement(By.id("Username")).clear();
    driver.findElement(By.id("Username")).sendKeys("invalid");
    // type | id=Password | bogus
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("bogus");
    // click | id=login | 
    driver.findElement(By.id("login")).click();
    // type | id=Password | bogus
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("bogus");
    // verifyText | css=span.field-validation-error | Invalid user name or password
    try {
      assertEquals("Invalid user name or password", driver.findElement(By.cssSelector("span.field-validation-error")).getText());
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
