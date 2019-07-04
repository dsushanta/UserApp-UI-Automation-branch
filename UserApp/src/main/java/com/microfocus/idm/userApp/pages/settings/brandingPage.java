package com.microfocus.idm.userApp.pages.settings;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class brandingPage extends uiBase {

    @FindBy(xpath = "//*[@id='Branding']/div/div/form/div/div[1]/div/div[1]/ul/li[2]/a/translate/span")             WebElement footerTab;
    @FindBy(id = "contactInfo_def")                                                                                 WebElement contact_info_field;
    @FindBy(xpath = "//*[@id='brandfooter']/button[1]")                                                             WebElement save_button;
    @FindBy(xpath = "//*[@id='confirmSaveBranding']/div/div/div/div/div[2]/div/button[1]")                          WebElement confirm_save;

    public brandingPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public brandingPage modifyContactInformationInFooter(String info) {

        footerTab.click();
        contact_info_field.sendKeys(info);
        save_button.click();
        confirm_save.click();
        return this;
    }
}
