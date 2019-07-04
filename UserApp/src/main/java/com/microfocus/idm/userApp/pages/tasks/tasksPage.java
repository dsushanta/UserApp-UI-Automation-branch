package com.microfocus.idm.userApp.pages.tasks;

import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by Sushant on 3/18/2017.
 */
public class tasksPage extends uiBase {

    @FindBy(id = "input-search")                                                                        WebElement task_search_field;
    @FindBy(xpath = "//task-content-table/div[@class='spacer-out-bottom-sm']//span[text()='Approve']")  WebElement approve_button;
    @FindBy(xpath = "//task-content-table/div[@class='spacer-out-bottom-sm']//span[text()='Deny']")     WebElement deny_button;
    @FindBy(xpath = "//task-content-table/div[@class='spacer-out-bottom-sm']//span[text()='Claim']")    WebElement claim_button;
    @FindBy(xpath = "//task-content-table/div[@class='spacer-out-bottom-sm']//span[text()='Release']")  WebElement release_button;
    @FindBy(id = "provide-comment")                                                                     List<WebElement> comment_fields;
    @FindBy(xpath = "//div[@class='modal-footer']//button[@type='submit' and text()='Deny']")           WebElement task_deny_button;
    @FindBy(xpath = "//div[@class='modal-footer']//button[@type='submit' and text()='Approve']")        List<WebElement> task_approve_buttons;
    @FindBy(xpath = "//div[@class='modal-footer']//button[@type='submit']//span[text()='Clear selection']") WebElement task_clear_selection_button;

    public tasksPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    WebElement getWebElementForTaskToSelect(String task_name, String recipient_name) {
        WebElement we_checkbox = null;
        String row_xpath = "//*[@id='main_content']/ui-content//task-content-table/table/tbody/tr";
        List<WebElement> tasks = driver.findElements(By.xpath(row_xpath));
        for(WebElement w : tasks) {
            String r_name = w.findElement(By.xpath("//td[4]/div[2]//a")).getText();
            String t_name = w.findElement(By.xpath("//td[2]/div[2]//a")).getText();
            boolean b1 = r_name.equalsIgnoreCase(recipient_name);
            boolean b2 = t_name.contains(task_name);
            if(b1 && b2) {
                we_checkbox = w.findElement(By.xpath("//td[1]//input[@type='checkbox']"));
                break;
            }
        }
        return we_checkbox;
    }

    public tasksPage approveSingleRequest(String task_name, String recipient_name) {

        task_search_field.sendKeys(task_name);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement task_check_box = getWebElementForTaskToSelect(task_name, recipient_name);
        js_executor.executeScript("arguments[0].click();", task_check_box);
        approve_button.click();
        WebElement comment = comment_fields.get(1);
        comment.sendKeys("Sure - I will get you a new MacBook");
        task_approve_buttons.get(1).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public tasksPage denySingleRequest(String task_name, String recipient_name) {

        WebElement task_search_field = driver.findElement(By.id("input-search"));
        task_search_field.sendKeys(task_name);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement task_check_box = getWebElementForTaskToSelect(task_name, recipient_name);
        //wait.until(ExpectedConditions.visibilityOf(task_check_box));
        js_executor.executeScript("arguments[0].click();", task_check_box);
        deny_button.click();
        WebElement comment = comment_fields.get(1);
        comment.sendKeys("No - You already have one");
        task_deny_button.click();
        return this;
    }
}
