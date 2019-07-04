package com.microfocus.idm.userApp.pages.people.users;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Sushant on 3/9/2017.
 */
public class usersPage extends uiBase {

    public @FindBy(xpath = "//*[@id='main_content']/ui-content/div/users-section/div[1]/div/div[1]/div/div[2]/div[1]/section/input")                               WebElement userSearch;
    public @FindBy(xpath = "//*[@id='main_content']/ui-content/div/users-section/div[1]/div/div[2]/div/users-content-list/div/section[2]/article/users-content-list-details-view/section[2]/div[1]/div/dynamic-content-type/div/div/div/a")	WebElement userMail;
    public @FindBy(xpath = "//*[@id='userGroupsContent']/ul/li/div/a")  WebElement grp;
    public @FindBy(xpath = "//*[@id='main_content']/ui-content/div/users-section/div[1]/div/div[2]/div/users-content-list/div/section[1]/div/div[1]/div/span/translate[1]/span") WebElement a;
    public @FindBy(xpath = "//button[@title='New user']")                                                                  WebElement new_user_button;
    public  @FindBy(xpath = "//button[@title='Manage Users']")                                                             WebElement manage_users_button;

    public usersPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public usersPage searchUser(String search_string) {
        wait.until(ExpectedConditions.visibilityOf(a));
        userSearch.sendKeys(search_string);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(a)));
        String mail = userMail.getText();
        System.out.println(mail);
        return this;
    }

    public usersPage navigateToCreateUserPage() {
        wait.until(ExpectedConditions.visibilityOf(new_user_button));
        new_user_button.click();
        return this;
    }

    public usersPage navigateToManageUsersPage() {
        //wait.until(ExpectedConditions.visibilityOf(new_user_button));
        manage_users_button.click();
        return this;
    }
}
