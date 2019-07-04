package com.microfocus.idm.UA.rough;

import com.microfocus.idm.userApp.utils.eventHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class rough_ranjit {

    private static WebDriver wDriver;
    public static EventFiringWebDriver driver;

    @Test
    public void login() {

        DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome ();
        handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        wDriver = new ChromeDriver(handlSSLErr);

        driver = new EventFiringWebDriver(wDriver);
        eventHandler eListener = new eventHandler();
        driver.register(eListener);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.get("https://joey.labs.blr.novell.com:8543/idmdash");
        //driver.navigate().to("javascript:document.getElementById('overridelink').click()"); Alternate way to handle SSL in Edge
        driver.manage().window().maximize();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("oauthframe"));
        driver.findElement(By.id("Ecom_User_ID")).sendKeys("uaadmin");
//        userField.sendKeys(userName);
        //pwdField.sendKeys(pwd);
        //loginButton.click();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
