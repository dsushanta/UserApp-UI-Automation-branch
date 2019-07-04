package com.microfocus.idm.UA.useCases.validation;

import com.microfocus.idm.UA.base.testBase;
import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.pages.access.requests.newRequestPage;
import com.microfocus.idm.userApp.pages.access.requests.permissions.permissionsPage;
import com.microfocus.idm.userApp.pages.loginPage;
import com.microfocus.idm.userApp.pages.navigateTo;
import com.microfocus.idm.userApp.pages.people.users.createUserPage;
import com.microfocus.idm.userApp.pages.people.users.manageUsersPage;
import com.microfocus.idm.userApp.pages.people.users.usersPage;
import com.microfocus.idm.userApp.pages.idmadmin.resourceCatalog;
import com.microfocus.idm.userApp.pages.settings.customizationPage;
import com.microfocus.idm.userApp.pages.sspr.ssprPage;
import com.microfocus.idm.userApp.pages.tasks.tasksPage;
import com.microfocus.idm.userApp.setup_creation.Test.createSetup;
import com.microfocus.idm.userApp.utils.loadEnvironment;
import com.microfocus.idm.userApp.utils.xml.extractFromRRAXml;
import com.microfocus.idm.userApp.utils.xml.extractFromRequestXml;
import com.microfocus.idm.userApp.utils.xml.extractFromUsersXml;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Sushant on 3/10/2017.
 */

@Title("Use Case - Create User and Resource assignment")
@Description("Test Cases included - Create a Resource, Create a User, make request on behalf, Approve request, Check the permission, Delete User and finally delete the resource")
public class firstSetOfUseCases extends testBase{

    uiBase userApp;

    //@Parameters({ "browser", "server" })
    @Parameters( "browser")

    @BeforeClass
    public void launch(String browser) {
        launchBrowser(browser);
    }

    @BeforeClass
    public void create_setup() {
        try {
            createSetup.generateCreateUserEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = { "UseCase1" })
    @Severity(SeverityLevel.BLOCKER)
    @Title("Create Resource without entitlement and add approver")
    @Description("This test will login to User Application as UserApp Admin, Create a Resource and add one user approver to it")
    public void createResourceWithoutEntitlement() {
        try {
            Map<String, String> modify_resource_details = extractFromRRAXml.getApproverDetailsToAddToAResource();
            String[] admin_credentials = extractFromUsersXml.getUserAppAdminCredentials();
            userApp = new loginPage(driver).login(admin_credentials[0], admin_credentials[1]);
            loadEnvironment.LOGGER.info("Logged into User App successfully !!");
            try {
                if(new ssprPage(driver).checkSSPRPage()) {
                    new ssprPage(driver).setupSecurityQuestions();
                    loadEnvironment.LOGGER.info("Security Questions - Responses set for "+admin_credentials[0]);
                }
            } catch(WebDriverException e) {
                loadEnvironment.LOGGER.info("Security Questions were already set for "+admin_credentials[0]+" in SSPR page");
            }
            userApp = new navigateTo(driver).customizationTab();
            userApp = new customizationPage(driver).setBaseContainerToUser();
            userApp = new navigateTo(driver).resourceCatalog();
            Map<String, String> create_resource_details = extractFromRRAXml.getCreateResourceWithoutEntDetails();
            userApp = new resourceCatalog(driver).createResourceWithoutEntitlement(create_resource_details);
            loadEnvironment.LOGGER.info("Resource - "+create_resource_details.get("name")+" Successfully created !!");
            userApp = new resourceCatalog(driver).addSerialApproverToResource(modify_resource_details);
            loadEnvironment.LOGGER.info("Approver - "+modify_resource_details.get("approver_value")+" added successfully to the Resource !!");
        }
        catch(Exception e) {
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Logging out !!! ");
            assertBoolean(true, false);
        }
    }

    @Test(dependsOnMethods = { "createResourceWithoutEntitlement" }, groups = { "UseCase1" })
    //@Test
    @Severity(SeverityLevel.BLOCKER)
    @Title("Create A User")
    @Description("This test will login to User Application as UserApp Admin and Create a User")
    public void createUser() {

        try {
            Map<String, String> user_details = extractFromUsersXml.getDetailsOfUserToBeCreated();
            userApp = new navigateTo(driver).usersPage();
            userApp = new usersPage(driver).navigateToCreateUserPage();
            userApp = new createUserPage(driver).createUser(user_details);
            loadEnvironment.LOGGER.info("New user "+user_details.get("full_name")+" has been created !!");
            userApp = new navigateTo(driver).applicationsPage();
        }
        catch(Exception e) {
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Failing the Test !!! ");
            assertBoolean(true, false);
        }
    }

    @Test(dependsOnMethods = { "createUser" }, groups = { "UseCase1" })
    @Severity(SeverityLevel.BLOCKER)
    @Title("Make ROB for a single user recipient and Single permission")
    @Description("This test will login to User Application as UserApp Admin, make ROB for a single user recipient and Single permission")
    public void makeROBSingleUserRecipientSinglePermission() {

        try {
            userApp = new navigateTo(driver).requestsTab();
            Map<String, String > recipient_details = extractFromUsersXml.getDetailsOfUserToBeCreated();
            String recipient_name = recipient_details.get("name");
            String recipient_full_name = recipient_details.get("full_name");
            String permission_to_request = extractFromRequestXml.getResourceToRequest();
            userApp = new newRequestPage(driver).makeROBForSingleUserAndSinglePermission(recipient_name, recipient_full_name, permission_to_request);
            loadEnvironment.LOGGER.info("Request has been made for the permission "+permission_to_request+" for recipient "+recipient_full_name);
            userApp = new navigateTo(driver).applicationsPage();
            new navigateTo(driver).logout();
        }
        catch(Exception e) {
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Failing the Test !!! ");
            assertBoolean(true, false);
        }
    }

    @Test(dependsOnMethods = { "makeROBSingleUserRecipientSinglePermission" }, groups = { "UseCase1" })
    //@Test(groups = { "UseCase1" })
    @Severity(SeverityLevel.BLOCKER)
    @Title("Approve a Request")
    @Description("This test will login to User Application as Approver and approve a single request from tasks page")
    public void approveSingleTask() {

        try {
            String[] approver_credentials = extractFromUsersXml.getApproverCredentials();
            userApp = new loginPage(driver).login(approver_credentials[0], approver_credentials[1]);
            loadEnvironment.LOGGER.info("Logged into idmdash successfully !!");
            try {
                if(new ssprPage(driver).checkSSPRPage()) {
                    new ssprPage(driver).setupSecurityQuestions();
                    loadEnvironment.LOGGER.info("Security Questions - Responses set for "+approver_credentials[0]);
                }
            } catch(WebDriverException e) {
                loadEnvironment.LOGGER.info("Security Questions were already set for "+approver_credentials[0]+" in SSPR page");
            }
            userApp = new navigateTo(driver).taskssTab();
            Map<String, String > recipient_details = extractFromUsersXml.getDetailsOfUserToBeCreated();
            String recipient_full_name = recipient_details.get("full_name");
            String permission_to_request = extractFromRequestXml.getResourceToRequest();
            userApp = new tasksPage(driver).approveSingleRequest(permission_to_request, recipient_full_name);
            loadEnvironment.LOGGER.info("Request has been approved by "+approver_credentials[0]);
            new navigateTo(driver).logout();
        }
        catch(Exception e) {
            new navigateTo(driver).logout();
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Failing the Test !!! ");
            assertBoolean(true, false);
        }
    }

    @Test(dependsOnMethods = { "approveSingleTask" }, groups = { "UseCase1" })
    //@Test
    @Severity(SeverityLevel.BLOCKER)
    @Title("Check User's permission after approval")
    @Description("This test will login to User Application as Recipient, navigate to permissions page and check the presence of the permission")
    public void checkUserPermissionAfterApproval() {

        try {
            String[] recipient_credentials = extractFromUsersXml.getRecipientCredentials();
            userApp = new loginPage(driver).login(recipient_credentials[0], recipient_credentials[1]);
            loadEnvironment.LOGGER.info("Logged into idmdash successfully !!");
            try {
                if(new ssprPage(driver).checkSSPRPage()) {
                    new ssprPage(driver).setupSecurityQuestions();
                    loadEnvironment.LOGGER.info("Security Questions and Responses have been set for "+recipient_credentials[0]);
                }
            } catch(WebDriverException e) {
                loadEnvironment.LOGGER.info("Security Questions were already set for "+recipient_credentials[0]+" in SSPR page");
            }
            userApp = new navigateTo(driver).permissionsTab();
            String permission_name = extractFromRequestXml.getResourceToRequest();
            boolean permission_found = new permissionsPage(driver).checkUserPermission(permission_name);
            assertBoolean(permission_found, true);
            loadEnvironment.LOGGER.info(permission_name+" has been assigned to "+recipient_credentials[0]+" !!");
            new navigateTo(driver).logout();
        }
        catch(Exception e) {
            new navigateTo(driver).logout();
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Failing the Test !!! ");
            assertBoolean(true, false);
        }
    }

    @Test(dependsOnMethods = { "checkUserPermissionAfterApproval" }, groups = { "UseCase1" })
    @Severity(SeverityLevel.BLOCKER)
    @Title("Delete User")
    @Description("This test will login to User Application as UserApp Admin and Create the user which was created earlier")
    public void deleteUser() {

        try {
            String[] admin_credentials = extractFromUsersXml.getUserAppAdminCredentials();
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
            userApp = new navigateTo(driver).usersPage();
            userApp = new usersPage(driver).navigateToManageUsersPage();
            Map<String, String > recipient_details = extractFromUsersXml.getDetailsOfUserToBeCreated();
            String recipient_email = recipient_details.get("email");
            String recipient_full_name = recipient_details.get("full_name");
            userApp = new manageUsersPage(driver).deleteUser(recipient_full_name, recipient_email);
            loadEnvironment.LOGGER.info(recipient_full_name+" has been deleted !!!");
            userApp = new navigateTo(driver).applicationsPage();
        }
        catch(Exception e) {
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Logging out !!! ");
            assertBoolean(true, false);
        }
    }

    @Test(dependsOnMethods = { "deleteUser" }, groups = { "UseCase1" })
    //@Test
    @Severity(SeverityLevel.BLOCKER)
    @Title("Delete a Resource")
    @Description("This test will login to User Application as UserApp Admin and delete a Resource")
    public void deleteResource() {

        try {
            userApp = new navigateTo(driver).resourceCatalog();
            String resource_name = extractFromRequestXml.getResourceToRequest();
            userApp = new resourceCatalog(driver).deleteResource(resource_name);
            loadEnvironment.LOGGER.info(resource_name+" has been deleted !!!");
            userApp = new navigateTo(driver).applicationsPage();
            new navigateTo(driver).logout();

        }
        catch(Exception e) {
            new navigateTo(driver).logout();
            loadEnvironment.LOGGER.error(e);
            loadEnvironment.LOGGER.info("Something has gone wrong - Logging out !!! ");
            assertBoolean(true, false);
        }
    }

    /*@Attachment(value = "UserAppLog", type = "text/plain")
    public static byte[] saveLogAttach(String attachName) {
        try {
            URL defaultLog = firstSetOfUseCases.class.getResource("/UserApp.log");
            File logFile = new File(defaultLog.toURI());
            return toByteArray(logFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] toByteArray(File file){
        byte[] b = null;
        try {
            b = Files.readAllBytes(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }*/
}
