package com.microfocus.idm.userApp.pages.people.users;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

/**
 * Created by Sushant on 3/11/2017.
 */
public class createUserPage extends uiBase {

    public @FindBy(id = "UserID")                                                                                          WebElement user_id_field;
    public @FindBy(id = "password")                                                                                        WebElement password_field;
    public @FindBy(id = "cpassword")                                                                                       WebElement confirm_password_field;
    public @FindBy(id = "FirstName")                                                                                       WebElement first_name_field;
    public @FindBy(id = "LastName")                                                                                        WebElement last_name_field;
    public @FindBy(id = "Title")                                                                                           WebElement title_field;
    public @FindBy(id = "Email")                                                                                           WebElement email_field;
    public @FindBy(id = "TelephoneNumber")                                                                                 WebElement telephone_field;
    public @FindBy(id = "manager")                                                                                         WebElement manager_field;
    public @FindBy(id = "directReports")                                                                                   WebElement direct_reports_field;
    public @FindBy(xpath = "//div[@class='form-footer']//button[@type='submit']")                                          WebElement create_user_button;
    public @FindBy(xpath = "//div[@class='form-footer']//button[@type='submit']//following-sibling::button")               WebElement cancel_button_field;
    public @FindBy(xpath = "//*[@id='create-user']//dynamic-input-type//multivalued-input[@input-id='FirstName']//button") WebElement add_first_name_button;
    public @FindBy(xpath = "//*[@id='create-user']//dynamic-input-type//multivalued-input[@input-id='LastName']//button")  WebElement add_last_name_button;
    public @FindBy(xpath = "//*[@id='create-user']//dynamic-input-type//multivalued-input[@input-id='Title']//button")     WebElement add_title_button;
    public @FindBy(xpath = "//*[@id='create-user']//dynamic-input-type//multivalued-input[@input-id='Email']//button")     WebElement add_email_button;
    public @FindBy(xpath = "//*[@id='create-user']//dynamic-input-type//multivalued-input[@input-id='TelephoneNumber']//button")  WebElement add_telephone_number_button;

    public createUserPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public createUserPage createUser(Map<String, String> user_details) {

        wait.until(ExpectedConditions.visibilityOf(user_id_field));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] parts = user_details.get("full_name").split("\\s+");
        user_id_field.sendKeys(user_details.get("name"));
        first_name_field.sendKeys(parts[0]);
        add_first_name_button.click();
        last_name_field.sendKeys(parts[1]);
        add_last_name_button.click();
        password_field.sendKeys(user_details.get("pwd"));
        confirm_password_field.sendKeys(user_details.get("pwd"));
        title_field.sendKeys(user_details.get("title"));
        add_title_button.click();
        email_field.sendKeys(user_details.get("email"));
        add_email_button.click();
        telephone_field.sendKeys(user_details.get("telephone"));
        add_telephone_number_button.click();
        js_executor.executeScript("arguments[0].click();", create_user_button);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
