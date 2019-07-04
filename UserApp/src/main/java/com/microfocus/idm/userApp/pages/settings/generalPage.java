package com.microfocus.idm.userApp.pages.settings;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Sushant on 7/22/2017.
 */
public class generalPage extends uiBase{

    @FindBy(id = "clientname")                                                                          WebElement clientDropdownField;

    public generalPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public generalPage selectClient(String client_name) {

        Select clientDropdown = new Select(clientDropdownField);
        clientDropdown.selectByVisibleText(client_name);
        return this;
    }
}
