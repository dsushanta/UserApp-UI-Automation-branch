package com.microfocus.idm.UA.rough;

import com.microfocus.idm.UA.base.testBase;
import com.microfocus.idm.UA.useCases.validation.firstSetOfUseCases;
import com.microfocus.idm.userApp.base.uiBase;
import com.microfocus.idm.userApp.pages.loginPage;
import com.microfocus.idm.userApp.pages.navigateTo;
import com.microfocus.idm.userApp.pages.people.users.usersPage;
import com.microfocus.idm.userApp.rough.r;
import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sushant on 3/10/2017.
 */
public class rough extends testBase {

    @Test
    public void A() {
        new r(driver).executeA();
        loadEnvironment.LOGGER.info("Logging from method A");
        /*String method_name = Thread.currentThread().getStackTrace()[1].getMethodName();
        saveImageAttach(method_name+".png");
        saveLogAttach("abc");*/
        assertBoolean(true, false);
    }

    @Test
    public void B() {
        new r(driver).executeB();
        loadEnvironment.LOGGER.info("Logging from method B");
        /*String method_name = Thread.currentThread().getStackTrace()[1].getMethodName();
        saveImageAttach(method_name+".png");
        saveLogAttach("abc");*/
        assertBoolean(true, true);
    }

    @Attachment(value = "UserAppLog", type = "text/plain")
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

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] saveImageAttach(String attachName) {
        try {
            System.out.println(attachName);
            URL defaultImage = firstSetOfUseCases.class.getResource("/"+attachName);
            File imageFile = new File(defaultImage.toURI());
            return toByteArray(imageFile);
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
    }
}
