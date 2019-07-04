package com.microfocus.idm.userApp.pages;

import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.pages.dashboard.dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Sushant on 3/9/2017.
 */
public class navigateTo extends uiBase {

    @FindBy(xpath = "//*[@id='navbar']//li[contains(@class,'dropdown')]/a[contains(text(),'People')]")                        WebElement peopleTab;
    @FindBy(xpath = "//*[@id='navbar']//ul[contains(@class,'dropdown-menu')]//li[@role='menuitem']//a[contains(text(),'Users')]") WebElement usersTab;
    @FindBy(xpath = "//*[@id='navbar']//li[contains(@class,'dropdown')]/a[contains(text(),'Access')]")                        WebElement accessTab;
    @FindBy(xpath = "//*[@id='navbar']//ul[@role='menubar']//ul[contains(@class,'dropdown-menu')]//a[text()='Request']")      WebElement requestsTab;
    @FindBy(xpath = "//*[@id='navbar']//ul[@role='menubar']//a[text()='Tasks']")                                    WebElement tasksTab;
    @FindBy(xpath = "//*[@id='navbar']//ul[@role='menubar']//a[text()='Application']")                              WebElement applicationsTab;
    @FindBy(xpath = "//*[@id='navbar']/ul/li[2]/a[1]")                                                              WebElement user_dropdown;
    @FindBy(xpath = "//*[@id='navbar']//ul[contains(@class,'dropdown-menu')]//span[contains(text(),'Sign out')]")                       WebElement signout_option;
    @FindBy(xpath = "//*[@id='navbar']//ul[@role='menubar']//ul[contains(@class,'dropdown-menu')]//a[text()='Request History']")  WebElement request_history_Tab;
    @FindBy(xpath = "//*[@id='navbar']//ul[@role='menubar']//ul[contains(@class,'dropdown-menu')]//a[text()='Proxy Assignment']")  WebElement proxy_assignment_Tab;
    @FindBy(xpath = "//*[@id='navbar']//ul[@role='menubar']//ul[contains(@class,'dropdown-menu')]//a[text()='Permissions']")  WebElement permissionTab;
    @FindBy(xpath = "//*[@id='navbar']/ul/li[2]/a[1]")                                                              WebElement dropDown;
    @FindBy(xpath = "//*[@id='navbar']//ul[@class='dropdown-menu']//a[contains(text(),'Settings')]")                WebElement settings;
    @FindBy(xpath = "//*[@id='BrandingToggle']/translate/span")                                                     WebElement brandingTab;
    @FindBy(xpath = "//*[@id='navbar']//a[contains(text(),'Administration')]")                                      WebElement administrationTab;
    @FindBy(xpath = "//*[@id='navbar']//a[contains(text(),'Resources')]")                                           WebElement resourceTab;
    @FindBy(xpath = "//*[@id='CustomizationTab']//span[contains(text(),'Customization')]")                          WebElement customizationTab;

    public @FindBy(xpath = ".//*[@id='navbar']/ul/li[1]/a/i")                                                       WebElement notification_icon;

    By discard_changes = By.xpath("//*[@id='confirmSkip']/div[contains(@class,'modal-dialog')]//span[contains(text(),'Discard Changes')]");

    public navigateTo(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public navigateTo resourceCatalog() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", administrationTab);
        js_executor.executeScript("arguments[0].click();", resourceTab);
        return this;
    }

    public navigateTo applicationsPage() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", applicationsTab);
        return this;
    }

    public navigateTo usersPage() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", peopleTab);
        js_executor.executeScript("arguments[0].click();", usersTab);
        return this;
    }

    public navigateTo requestsTab() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", accessTab);
        js_executor.executeScript("arguments[0].click();", requestsTab);
        return this;
    }

    public navigateTo taskssTab() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", tasksTab);
        return this;
    }

    public void logout() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", user_dropdown);
        wait.until(ExpectedConditions.visibilityOf(signout_option));
        js_executor.executeScript("arguments[0].click();", signout_option);
//        signout_option.click();
    }

    public navigateTo permissionsTab() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", accessTab);
        js_executor.executeScript("arguments[0].click();", permissionTab);
        return this;
    }

    public navigateTo generalSettingsPage() {
        wait.until(ExpectedConditions.visibilityOf(notification_icon));
        js_executor.executeScript("arguments[0].click();", dropDown);
        js_executor.executeScript("arguments[0].click();", settings);
        return this;
    }
    public navigateTo brandingTab() {
        generalSettingsPage();
        js_executor.executeScript("arguments[0].click();", brandingTab);
        Boolean b = isElementVisible(discard_changes);
        if(b)
            driver.findElement(By.xpath("//*[@id='confirmSkip']/div[contains(@class,'modal-dialog')]//div[contains(@class,'modal-footer')]//button//span[contains(text(),'Yes')]")).click();
        return this;
    }

    public navigateTo customizationTab() {
        generalSettingsPage();
        js_executor.executeScript("arguments[0].click();", customizationTab);
        Boolean b = isElementVisible(discard_changes);
        if(b)
            driver.findElement(By.xpath("//*[@id='confirmSkip']/div[contains(@class,'modal-dialog')]//div[contains(@class,'modal-footer')]//button//span[contains(text(),'Yes')]")).click();
        return this;
    }
}
