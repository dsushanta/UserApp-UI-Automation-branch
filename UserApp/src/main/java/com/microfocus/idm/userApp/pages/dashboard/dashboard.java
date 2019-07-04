package com.microfocus.idm.userApp.pages.dashboard;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sushant on 3/9/2017.
 */
public class dashboard extends uiBase {

    public dashboard(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
