package com.microfocus.idm.userApp.pages.access.requests.permissions;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by user on 3/25/2017.
 */
public class permissionsPage extends uiBase {

    @FindBy(id = "input-search")                                                                                    WebElement permission_search_field;
    @FindBy(xpath = "//*[@id='main_content']//span[@class='input-group-btn']//button[@data-toggle='dropdown']")     WebElement simple_filter_dropdown;
    @FindBy(xpath = "//span[@class='input-group-btn open']//span[text()='RESOURCE']")                               WebElement resource_filter;


    public permissionsPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean checkUserPermission(String permission) {
        boolean found;
        js_executor.executeScript("arguments[0].click();", simple_filter_dropdown);
        js_executor.executeScript("arguments[0].click();", resource_filter);
        permission_search_field.sendKeys(permission);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        found = matchPermission(permission);
        return found;
    }

    boolean matchPermission(String permission) {
        boolean found = false;
        String row_xpath = "//*[@id='permissions']/tbody/tr";
        List<WebElement> permissions = driver.findElements(By.xpath(row_xpath));
        for(WebElement w : permissions) {
            String p_name = w.findElement(By.xpath("//td[2]//strong")).getText();
            if(p_name.equalsIgnoreCase(permission)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
