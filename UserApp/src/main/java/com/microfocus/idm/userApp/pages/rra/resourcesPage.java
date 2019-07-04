package com.microfocus.idm.userApp.pages.rra;

import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.utils.commonUtils;
import com.microfocus.idm.userApp.utils.xml.extractFromRRAXml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

/**
 * Created by Sushant on 3/12/2017.
 */
public class resourcesPage extends uiBase {

    @FindBy(xpath = "//*[@id='ROLE']/a")                                                                                    WebElement manage_role_tab;
    @FindBy(xpath = "//*[@id='RESOURCE']/a")                                                                                WebElement manage_resource_tab;
    @FindBy(xpath = "//*[@id='resourceListPanel']/button")                                                                  WebElement new_resource_button;
    @FindBy(xpath = "//*[@id='deleteResourceButtonWrapper']/button")                                                        WebElement delete_resource_button;
    @FindBy(id = "resourceCreationTypeControl")                                                                             WebElement resource_type_dropdown;
    @FindBy(id = "createResourceIDControl")                                                                                 WebElement id_field;
    @FindBy(id = "createResourceNameControl")                                                                               WebElement name_field;
    @FindBy(id = "createResourceDescriptionControl")                                                                        WebElement description_field;
    @FindBy(id = "createResourceButtonsButton1")                                                                            WebElement create_resource_apply_button;
    @FindBy(id = "createResourceButtonsButton2")                                                                            WebElement create_resource_cancel_button;
    @FindBy(xpath = "//*[@id='resourceListSearchBoxContainer']/input")                                                      WebElement search_resource_field;
    @FindBy(id = "resourceDetailsPanel")                                                                                    WebElement resource_details_panel;
    @FindBy(id = "resourceButton1")                                                                                         WebElement apply_button;
    @FindBy(id = "resourceButton2")                                                                                         WebElement cancel_button;
    @FindBy(id = "resourceApprovalsControl")                                                                                WebElement grant_approval_type_dropdown;
    @FindBy(id = "resourceRevokeApprovalsControl")                                                                          WebElement revoke_approval_type_dropdown;
    @FindBy(id = "resourceApproversControl")                                                                                WebElement select_approver_control;
    @FindBy(xpath = "//*[@id='resourceApproversControl']//span[@title='Add Users']")                                        WebElement approver_type_is_user;
    @FindBy(xpath = "//*[@id='resourceApproversControl']//input[@placeholder='Search Users']")                              WebElement search_user_approver_field;
    @FindBy(xpath = "//*[@id='headerName']/a[@class='userNameHeader']")                                                     WebElement header_name;
    @FindBy(xpath = "//*[@id='headerMenuItem_logout']/a")                                                                   WebElement logout_menu_item;
    @FindBy(xpath = "//*[@id='deleteResourceButtonRow']/button[1]")                                                         WebElement delete_confirmation_button;

    public resourcesPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private WebElement makeXpathToSelectResource(String resource_name) {
        String locator = "//*[@id='resourceMainListTable_cbTable']//div[@class='largeText clickable nameList roleresourceListEllipsis' and @title='" + resource_name + "']";
        WebElement we = driver.findElement(By.xpath(locator));
        return we;
    }

    private WebElement makeXpathToSelectUserAsApprover(String user_full_name) {
        String locator = "//*[@id='resourceApproversControl']//li[@title='" + user_full_name + "']";
        WebElement we = driver.findElement(By.xpath(locator));
        return we;
    }

    public resourcesPage createResourceWithoutEntitlement(Map<String, String> create_resource_details) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js_executor.executeScript("arguments[0].click();", manage_resource_tab);
        wait.until(ExpectedConditions.visibilityOf(new_resource_button));
        js_executor.executeScript("arguments[0].click();", new_resource_button);
        Select resource_type = new Select(resource_type_dropdown);
        resource_type.selectByValue("Resource without Entitlement");
        wait.until(ExpectedConditions.visibilityOf(id_field));
        id_field.sendKeys(create_resource_details.get("id"));
        description_field.sendKeys(create_resource_details.get("description"));
        js_executor.executeScript("arguments[0].click();", create_resource_apply_button);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public resourcesPage addSerialApproverToResource(Map<String, String> modify_resource_details) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js_executor.executeScript("arguments[0].click();", manage_resource_tab);
        search_resource_field.sendKeys(modify_resource_details.get("name"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searched_resource = makeXpathToSelectResource(modify_resource_details.get("name"));
        js_executor.executeScript("arguments[0].click();", searched_resource);
        resource_details_panel.click();
        Select approval_type = new Select(grant_approval_type_dropdown);
        approval_type.selectByValue(modify_resource_details.get("approval_process"));
        select_approver_control.click();
        js_executor.executeScript("arguments[0].click();", approver_type_is_user);
        String[] approver_names_split = modify_resource_details.get("approver_value").split("\\s+");
        String approver_first_name = approver_names_split[0];
        search_user_approver_field.sendKeys(approver_first_name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement user_approver = makeXpathToSelectUserAsApprover(modify_resource_details.get("approver_value"));
        js_executor.executeScript("arguments[0].click();", user_approver);
        js_executor.executeScript("arguments[0].click();", apply_button);
        return this;
    }

    public resourcesPage deleteResource(String resource_name) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js_executor.executeScript("arguments[0].click();", manage_resource_tab);
        search_resource_field.sendKeys(resource_name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searched_resource = makeXpathToSelectResource(resource_name);
        js_executor.executeScript("arguments[0].click();", searched_resource);
        js_executor.executeScript("arguments[0].click();", delete_resource_button);
        js_executor.executeScript("arguments[0].click();", delete_confirmation_button);
        return this;
    }

    public resourcesPage logoutFromRRA() {

        js_executor.executeScript("arguments[0].click();", header_name);
        js_executor.executeScript("arguments[0].click();", logout_menu_item);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().to(commonUtils.makeBaseURL("http"));
        return this;
    }
}
