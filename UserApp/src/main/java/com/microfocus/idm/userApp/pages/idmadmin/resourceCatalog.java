package com.microfocus.idm.userApp.pages.idmadmin;

import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.utils.commonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Sushant on 10/31/2017.
 */

public class resourceCatalog extends uiBase {

    /*@FindBy(xpath = "//*[@id='ROLE']/a")                                                                                    WebElement manage_role_tab;
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
    @FindBy(xpath = "//*[@id='deleteResourceButtonRow']/button[1]")                                                         WebElement delete_confirmation_button;*/

    @FindBy(xpath = "//*[@id='main_content']/idm-resources-table//button[@title='New Resource']")                           WebElement create_new_resource_button;
    @FindBy(xpath = "//*[@id='main_content']/idm-create-resource//span[text()='Without Entitlement']")                      WebElement without_entitlement_button;
    @FindBy(xpath = "//*[@id='create-resource']//input[@formcontrolname='id']")                                             WebElement id_field;
    @FindBy(xpath = "//*[@id='create-resource']/form/div[2]/div/div/span[2]/input")                                         WebElement name_field;
    @FindBy(xpath = "//*[@id='create-resource']/form/div[3]/div/div/span[2]/textarea")                                      WebElement description_field;
    @FindBy(id = "input-search")                                                                                            WebElement search_resource_field;
    @FindBy(xpath = "//*[contains(text(),'Details, Owners, and Approvals')]")                                               WebElement resource_details_panel;
    @FindBy(xpath = "//select[contains(@name,'grantApprovalProcess')]")                                                          WebElement grant_approval_type_dropdown;
    @FindBy(xpath = "//*[@id='resource-details']//div[contains(text(),'Select Users, Groups, Roles or Containers')]")       WebElement select_approver_control;
    @FindBy(xpath = "//div[contains(text(),'Select Users, Groups, Roles or Containers')]//following-sibling::div//a[text()='Users']")    WebElement approver_type_is_user;
    @FindBy(xpath = "//input[contains(@placeholder,'Search users by First Name, Last Name, Email, Title,Department, Region')]")     WebElement search_user_approver_field;
    @FindBy(xpath = "//*[@id='create-resource']//button[@type='submit' and contains(text(),'Create Resource')]")                        WebElement create_resource_button;
    @FindBy(xpath = "//*[@id='resource-details']//button[@type='submit' and contains(text(),'Apply')]")                        WebElement resource_apply_button;
    @FindBy(xpath = "//*[@id='confirmation']//span[contains(text(),'Confirm')]//parent::button")                            WebElement delete_confirmation_button;
    @FindBy(xpath = "//*[@id='main_content']/idm-resources-table//idm-table//button/span[contains(text(),'Edit')]")         WebElement edit_resource_button;
    @FindBy(xpath = "//button[@title='Delete Role']")                                                                       WebElement delete_resource_button;
    @FindBy(xpath = "//*[@id='resource-details']//button[text()='Apply']")                                                  WebElement apply_button;
    //@FindBy(xpath = "//*[@id='resource-details']//form//dynamic-form//label[contains(text(),'Resource Info')]//following-sibling::input")   WebElement resource_info_field;

    public resourceCatalog(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private WebElement makeXpathToSelectResource(String resource_name) {
        WebElement res_checkbox = null;
        List<WebElement> resource_row_webelements = driver.findElements(By.xpath("//*[@id='main_content']/idm-resources-table//idm-table//table//tr"));
        for(WebElement w : resource_row_webelements) {
            String res_name = w.findElement(By.xpath("//idm-ui-display//a")).getText();
            if(res_name.equalsIgnoreCase(resource_name))
                res_checkbox = w.findElement(By.xpath("//input[@type='checkbox']"));
        }
        return res_checkbox;
    }

    private WebElement makeXpathToSelectUserAsApprover(String user_full_name) {
        /*String locator = "//*[@id='resourceApproversControl']//li[@title='" + user_full_name + "']";
        WebElement we = driver.findElement(By.xpath(locator));
        return we;*/
        /*WebElement we = null;
        List<WebElement> approver_row_webelements = driver.findElements(By.xpath("//*[@id='dropdown-container']//li"));
        for(WebElement w : approver_row_webelements) {
            String name = w.findElement(By.xpath("//a")).getText();
            if(name.contains(user_full_name))
                we = w.findElement(By.xpath("//a"));
        }
        return we;*/

        WebElement we = null;
        List<WebElement> approver_row_webelements = driver.findElements(By.xpath("//*[@id='dropdown-container']//a"));
        for(WebElement w : approver_row_webelements) {
            String name = w.getText();
            name = name.trim();
            if(name.contains(user_full_name)) {
                we = w;
                break;
            }
        }
        return we;
    }

    public resourceCatalog createResourceWithoutEntitlement(Map<String, String> create_resource_details) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //js_executor.executeScript("arguments[0].click();", manage_resource_tab);
        wait.until(ExpectedConditions.visibilityOf(create_new_resource_button));
        js_executor.executeScript("arguments[0].click();", create_new_resource_button);
        without_entitlement_button.click();
        wait.until(ExpectedConditions.visibilityOf(id_field));
        id_field.sendKeys(create_resource_details.get("id"));
        description_field.sendKeys(create_resource_details.get("description"));
        wait.until(ExpectedConditions.elementToBeClickable(create_resource_button));
        js_executor.executeScript("arguments[0].click();", create_resource_button);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public resourceCatalog addSerialApproverToResource(Map<String, String> modify_resource_details) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search_resource_field.sendKeys(modify_resource_details.get("name"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searched_resource = makeXpathToSelectResource(modify_resource_details.get("name"));
        js_executor.executeScript("arguments[0].click();", searched_resource);
        js_executor.executeScript("arguments[0].click();", edit_resource_button);
        resource_details_panel.click();
        Select approval_type = new Select(grant_approval_type_dropdown);
        approval_type.selectByVisibleText(modify_resource_details.get("approval_process"));
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
        By by_resource_info_field = By.xpath("//*[@id='resource-details']//form//dynamic-form//label[contains(text(),'Resource Info')]//following-sibling::input");

        if(isElementVisible(by_resource_info_field)) {
            WebElement resource_info_field = driver.findElement(by_resource_info_field);
            resource_info_field.sendKeys("1");
        }
        js_executor.executeScript("arguments[0].click();", apply_button);
        return this;
    }

    public resourceCatalog deleteResource(String resource_name) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //js_executor.executeScript("arguments[0].click();", manage_resource_tab);
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

    /*public resourceCatalog logoutFromRRA() {

        js_executor.executeScript("arguments[0].click();", header_name);
        js_executor.executeScript("arguments[0].click();", logout_menu_item);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().to(commonUtils.makeBaseURL("http"));
        return this;
    }*/
}
