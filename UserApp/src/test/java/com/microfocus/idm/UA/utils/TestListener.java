package com.microfocus.idm.UA.utils;

import com.microfocus.idm.UA.base.testBase;
import com.microfocus.idm.UA.useCases.validation.firstSetOfUseCases;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by Sushant on 4/8/20177.
 */
public class TestListener implements ITestListener, ISuiteListener {

    WebDriver driver = null;

    private void captureScreenShot(String methodName) {
        driver = testBase.getDriver();
        File srcFile  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/src/main/resources/"+methodName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("***Placed screen shot in Project's Root Folder ***");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(iTestResult.getName()+" started execution");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getName()+" finished execution successfully");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("***** Error ------- "+iTestResult.getName()+" test has failed *****");
//        String methodName=iTestResult.getName().toString().trim();
//        captureScreenShot(methodName);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    @Override
    public void onStart(ISuite iSuite) {

    }

    @Override
    public void onFinish(ISuite iSuite) {

    }
}

