package com.microfocus.idm.userApp.utils.screenshotAndRecording;

import java.io.File;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sushant on 3/25/2017.
 */
public class screen extends loadEnvironment {

    public static void captureScreenshot(EventFiringWebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
//            FileUtils.copyFile(source, new File(screenshotName+".png"));
            LOGGER.info("Screenshot taken");
        }
        catch (Exception e) {
            System.out.println("Exception while taking screen "+e.getMessage());
        }
    }
}
;