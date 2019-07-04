package com.microfocus.idm.userApp.pages.settings;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.microfocus.idm.userApp.base.uiBase;

import java.util.List;

public class customizationPage extends uiBase {

    @FindBy(xpath = "//*[@id='BaseContainer']//div[contains(@role,'menu')]//span[contains(text(),'Select Container')]")    WebElement select_container_dropdown;
    @FindBy(xpath = "//*[@id='customfooter']//span[text()='Save']//ancestor::button")                                              WebElement save_button;
    @FindBy(xpath = "//*[@id='confirmSavecustom']/div/div/div/div/div[2]//button//span[contains(text(),'Save')]")          WebElement save_confirm;

    By user_container_locator = By.xpath("//*[@id='BaseContainer']//ul[contains(@class,'selected-tags')]//span[contains(text(),'users')]");

    public customizationPage(EventFiringWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public customizationPage setBaseContainerToUser() {
        By root_container_expanded = By.xpath("//*[@id='BaseContainer']//data-drop-down//data-tree[@data-family='rootContainer']//ul/li/i[contains(@class,'expanded')]");
        if(!isElementVisible(user_container_locator)) {
            select_container_dropdown.click();
            if(!isElementVisible(root_container_expanded)) {
                List<WebElement> collapsed_list = driver.findElements(By.xpath("//*[@id='BaseContainer']//data-drop-down//data-tree[@data-family='rootContainer']//ul/li/i[contains(@class,'collapsed')]"));
                collapsed_list.get(0).click();
            }
            driver.findElement(By.xpath("//*[@id='BaseContainer']//data-drop-down//data-tree[@data-family='rootContainer']//span[contains(text(),'users')]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(save_button));
            //js_executor.executeScript("arguments[0].click();",save_button);
            save_button.click();
            //save_button.sendKeys(Keys.TAB);
            //save_button.sendKeys(Keys.RETURN);
            //action.moveToElement(save_button).click().perform();
            save_button.sendKeys(Keys.RETURN);
            wait.until(ExpectedConditions.visibilityOf(save_confirm));
            save_confirm.click();
        }
        loadEnvironment.LOGGER.info("User base Container has been changed to Users");
        return this;
    }
}
