package com.microfocus.idm.userApp.pages.people.users;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sushant on 3/11/2017.
 */
public class manageUsersPage extends uiBase {

    public @FindBy(id = "input-search")                                                                                    WebElement search_field;
    public @FindBy(id = "//div[@class='content-loading']//span[contains(text(),'No users found')]")                        WebElement no_users_found_test;                                                               WebElement confirm_password_field;
    public @FindBy(id = "removeUsersButton")                                                                               WebElement remove_user_button;
    public @FindBy(id = "rightButton")                                                                                     WebElement confirm_delete_button;
    public @FindBy(xpath = "//button//span[contains(text(),'Edit done')]")                                                 WebElement edit_done_button;
    public @FindBy(xpath = "//data-feedback-dir//div[contains(text(),'deleted successfully')]")                            WebElement deleted_message;

    public manageUsersPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private WebElement makeXpathToSearchUser(String full_name) {
        String locator = "//input[@type='checkbox' and @aria-label='" + full_name + "']";
        WebElement we = driver.findElement(By.xpath(locator));
        return we;
    }

    public manageUsersPage deleteUser(String full_name, String email) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search_field.sendKeys(email);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searched_user_checkbox_field = makeXpathToSearchUser(full_name);
        js_executor.executeScript("arguments[0].click();", searched_user_checkbox_field);
        remove_user_button.click();
        confirm_delete_button.click();
        edit_done_button.click();
        return this;
    }

}
