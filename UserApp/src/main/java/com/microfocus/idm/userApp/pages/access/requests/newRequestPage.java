package com.microfocus.idm.userApp.pages.access.requests;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sushant on 3/14/2017.
 */

public class newRequestPage extends uiBase {

    @FindBy(id = "make-a-request-button")                                                               WebElement new_request_button;
    @FindBy(xpath = "//input[@type='radio' and @value='other' and @name='request_for']")                WebElement others_button;
    @FindBy(xpath = "//div[@class='request']")                                                          WebElement recipients_field;
    @FindBy(xpath = "//div[@class='spacer-in-sm']//span[contains(text(),'Users')]")                     WebElement users_recipient_tab;
    @FindBy(id = "dashMemberSearchField_users")                                                         WebElement users_recipient_field;
    @FindBy(id = "permissions")                                                                         WebElement permission_field;
    @FindBy(id = "reasonForRequest")                                                                    WebElement reason_field;
    @FindBy(xpath = "//button[@type='submit']//span[contains(text(),'Request')]")                       WebElement request_button;
    @FindBy(xpath = "//button[@type='button']//span[contains(text(),'Cancel')]")                        WebElement cancel_button;
    @FindBy(id = "effectiveDate")                                                                       WebElement effective_date_field;
    @FindBy(id = "expirationDate")                                                                      WebElement expiration_date_field;
    @FindBy(id = "ResourceRequestReason")                                                               WebElement resource_request_reason;
    @FindBy(xpath = "//*[@id='resourceRequestFormModal']//button//span[text()='Request']")              WebElement resource_form_request_button;
    public newRequestPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private WebElement getWebElementForUserRecipientSelection(String user_full_name) {
        String locator = "//ul[@class='list-selector']//a[text()='" + user_full_name + "']";
        WebElement we = driver.findElement(By.xpath(locator));
        return we;
    }

    private WebElement getWebElementForPermissionSelection(String permission_name) {
        String locator = "//ul[@id='permissions-list']//p[text()='" + permission_name + "']";
        WebElement we = driver.findElement(By.xpath(locator));
        return we;
    }

    public newRequestPage makeROBForSingleUserAndSinglePermission(String user_first_name, String user_full_name, String permission_name) {
        new_request_button.click();
        others_button.click();
        recipients_field.click();
        users_recipient_tab.click();
        users_recipient_field.click();
        users_recipient_field.sendKeys(user_first_name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement recipient_selected = getWebElementForUserRecipientSelection(user_full_name);
        recipient_selected.click();

        permission_field.click();
        //permission_field.click();
        permission_field.sendKeys(permission_name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement permission_selected = getWebElementForPermissionSelection(permission_name);
        permission_selected.click();
        /*resource_request_reason.sendKeys("Can I get a MacBook please");
        resource_form_request_button.click();*/
        reason_field.sendKeys("Can I get a MacBook please");
        request_button.click();
        return this;
    }
}
