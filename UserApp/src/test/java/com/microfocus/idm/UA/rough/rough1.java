package com.microfocus.idm.UA.rough;

import com.microfocus.idm.UA.base.testBase;
import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.pages.loginPage;
import com.microfocus.idm.userApp.pages.navigateTo;
import com.microfocus.idm.userApp.pages.people.users.createUserPage;
import com.microfocus.idm.userApp.pages.people.users.usersPage;
import com.microfocus.idm.userApp.pages.settings.brandingPage;
import com.microfocus.idm.userApp.pages.sspr.ssprPage;
import com.microfocus.idm.userApp.utils.loadEnvironment;
import com.microfocus.idm.userApp.utils.xml.extractFromUsersXml;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class rough1 extends testBase {

    uiBase userApp;

    @BeforeClass
    public void launch() {
        launchBrowser("chrome");
    }

    @Test
    public void TestSaveContactInfoInFooter() {
        try {
            String[] admin_credentials = extractFromUsersXml.getUserAppAdminCredentials();
            Map<String, String> user_details = extractFromUsersXml.getDetailsOfUserToBeCreated();
            userApp = new loginPage(driver).login(admin_credentials[0], admin_credentials[1]);
            loadEnvironment.LOGGER.info("Logged into idmdash successfully !!");
            try {
                if(new ssprPage(driver).checkSSPRPage()) {
                    new ssprPage(driver).setupSecurityQuestions();
                    loadEnvironment.LOGGER.info("Security Questions - Responses set for "+admin_credentials[0]);
                }
            } catch(WebDriverException e) {
                loadEnvironment.LOGGER.info("Security Questions were already set for "+admin_credentials[0]+" in SSPR page");
            }
            userApp = new navigateTo(driver).brandingTab();
            userApp = new brandingPage(driver).modifyContactInformationInFooter("111");
        }
        catch(Exception e) {
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Failing the Test !!! ");
            assertBoolean(true, false);
        }
    }
}
