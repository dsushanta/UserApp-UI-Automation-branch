package com.microfocus.idm.userApp.pages.sspr;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.microfocus.idm.userApp.base.uiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Sushant on 3/25/2017.
 */
public class ssprPage extends uiBase {

    @FindBy(id = "page-content-title")                                                                  WebElement  page_title_field;
    @FindBy(id = "PwmResponse_Q_Random_0")                                                              WebElement  question_field_1;
    @FindBy(id = "PwmResponse_Q_Random_1")                                                              WebElement  question_field_2;
    @FindBy(id = "PwmResponse_Q_Random_2")                                                              WebElement  question_field_3;
    @FindBy(id = "PwmResponse_Q_Random_3")                                                              WebElement  question_field_4;
    @FindBy(id = "PwmResponse_R_Random_0")                                                              WebElement  answer_field_1;
    @FindBy(id = "PwmResponse_R_Random_1")                                                              WebElement  answer_field_2;
    @FindBy(id = "PwmResponse_R_Random_2")                                                              WebElement  answer_field_3;
    @FindBy(id = "PwmResponse_R_Random_3")                                                              WebElement  answer_field_4;
    @FindBy(id = "message")                                                                             WebElement  message;
    @FindBy(id = "button-setResponses")                                                                 WebElement  save_answers_button;
    @FindBy(id = "page-content-title")                                                                  WebElement  save_success_message;
    @FindBy(id = "submitBtn")                                                                           WebElement  continue_button;

    String q1 = "What is the name of the main character in your favorite book?";
    String q2 = "What is the name of your favorite pet?";
    String q3 = "What is your partner's nickname?";
    String q4 = "What is your favorite vehicle?";

    String a1 = "b1234";
    String a2 = "p1234";
    String a3 = "n1234";
    String a4 = "v1234";

    public ssprPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkSSPRPage() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("oauthframe"));
        if(page_title_field.isDisplayed())
            return true;
        else
            return false;
    }

    public ssprPage setupSecurityQuestions() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("oauthframe"));
        wait.until(ExpectedConditions.visibilityOf(page_title_field));
        Select security_question_1 = new Select(question_field_1);
        security_question_1.selectByVisibleText(q1);
        answer_field_1.sendKeys(a1);
        Select security_question_2 = new Select(question_field_2);
        security_question_2.selectByVisibleText(q2);
        answer_field_2.sendKeys(a2);
        Select security_question_3 = new Select(question_field_3);
        security_question_3.selectByVisibleText(q3);
        answer_field_3.sendKeys(a3);
        Select security_question_4 = new Select(question_field_4);
        security_question_4.selectByVisibleText(q4);
        answer_field_4.sendKeys(a4);
        wait.until(ExpectedConditions.elementToBeClickable(save_answers_button));
        js_executor.executeScript("arguments[0].click();", save_answers_button);
        wait.until(ExpectedConditions.visibilityOf(save_success_message));
        js_executor.executeScript("arguments[0].click();", continue_button);
        driver.switchTo().defaultContent();
        return this;
    }
}
