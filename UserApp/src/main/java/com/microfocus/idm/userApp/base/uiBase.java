package com.microfocus.idm.userApp.base;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sushant on 3/9/2017.
 */
public class uiBase extends loadEnvironment {

    // TODO - Log informations

    public static EventFiringWebDriver driver;
    public static WebDriverWait wait;
    public JavascriptExecutor js_executor;
    public Actions action;

    public uiBase(EventFiringWebDriver driver) {
        this.driver = driver;
        js_executor = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, IMPLICIT_TIMEOUT);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        action = new Actions(driver);
    }

    public boolean isElementPresent(By b) {
        try {
            driver.findElement(b);
            return true;
        }catch(NoSuchElementException e) {
            loadEnvironment.LOGGER.info("Following element is not present : "+b.toString());
            return false;
        }
    }

    public boolean isElementVisible(By b) {
        try {
            if(driver.findElement(b).isDisplayed())
                return true;
            else
                return false;
        }catch(Exception e) {
            loadEnvironment.LOGGER.info("Following element is not visible : "+b.toString());
            return false;
        }
    }
}
