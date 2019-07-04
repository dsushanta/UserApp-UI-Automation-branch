package com.microfocus.idm.userApp.pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.pages.dashboard.dashboard;
import com.microfocus.idm.userApp.pages.sspr.ssprPage;
import com.microfocus.idm.userApp.utils.commonUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Sushant on 3/9/2017.
 */
public class loginPage extends uiBase {

    @FindBy(id = "Ecom_User_ID")                                                    WebElement userField;
    @FindBy(id = "Ecom_Password")                                                   WebElement pwdField;
    @FindBy(xpath = "//button[@name='loginButton2']")                               WebElement loginButton;

    public loginPage(EventFiringWebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public loginPage login(String userName, String pwd) {

        //String URL = commonUtils.makeBaseURL("https");
        String URL = commonUtils.makeBaseURL("http");
        driver.get(URL);
        //driver.navigate().to("javascript:document.getElementById('overridelink').click()"); Alternate way to handle SSL in Edge
        driver.manage().window().maximize();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("oauthframe"));
        userField.sendKeys(userName);
        pwdField.sendKeys(pwd);
        loginButton.click();
        driver.switchTo().defaultContent();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    /*public loginPage loginToRRA(String userName, String pwd) {

        //String URL = commonUtils.makeBaseURLForRRA("https");
        String URL = commonUtils.makeBaseURLForRRA("http");
        driver.get(URL);
        driver.manage().window().maximize();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("spiffyuoauthframe"));
        userField.sendKeys(userName);
        pwdField.sendKeys(pwd);
        loginButton.click();
        driver.switchTo().defaultContent();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        wait.until(ExpectedConditions.visibilityOf(new dashboard(driver).notification_icon));
        return this;
    }*/
}
