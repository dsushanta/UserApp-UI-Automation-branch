package com.microfocus.idm.userApp.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class eventHandler implements WebDriverEventListener, InvocationHandler {

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Accepting the Alert !!");
        System.out.println("Before Accepting the Alert !!");
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Accepting the Alert !!");
        System.out.println("After Accepting the Alert !!");
    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Dismissing the Alert !!");
        System.out.println("After Dismissing the Alert !!");
    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Dismissing the Alert !!");
        System.out.println("Before Dismissing the Alert !!");
    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Navigating to the URL : "+s);
        System.out.println("Before Navigating to the URL : "+s);
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Navigating to the URL : "+s);
        System.out.println("After Navigating to the URL : "+s);
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Navigating back !!");
        System.out.println("Before Navigating back !!");
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Navigating back !!");
        System.out.println("After Navigating back !!");
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Navigating forward !!");
        System.out.println("Before Navigating forward !!");
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Navigating forward !!");
        System.out.println("After Navigating forward !!");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Navigating Refresh !!");
        System.out.println("Before Navigating Refresh !!");
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Navigating Refresh !!");
        System.out.println("After Navigating Refresh !!");
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before finding the following Web Element : ["+webElement+"]");
        System.out.println("Before finding the following Web Element : ["+webElement+"]");
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Found the following Web Element : ["+webElement+"]");
        System.out.println("Found the following Web Element : ["+webElement+"]");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before clicking on the following Web Element : ["+webElement.toString()+"]");
        System.out.println("Before clicking on the following Web Element : ["+webElement.toString()+"]");
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Clicked on the following Web Element : ["+webElement.toString()+"]");
        System.out.println("Clicked on the following Web Element : ["+webElement.toString()+"]");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        loadEnvironment.LOGGER.info("Values of the Following Web Element : ["+webElement+"] will be changed to : "+charSequences.toString());
        System.out.println("Values of the Following Web Element : ["+webElement+"] will be changed to : "+charSequences.toString());
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        loadEnvironment.LOGGER.info("Values of the Following Web Element : ["+webElement+"] has been changed to : "+charSequences.toString());
        System.out.println("Values of the Following Web Element : ["+webElement+"] has been changed to : "+charSequences.toString());
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("Before Script !!");
        System.out.println("Before Script !!");
    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {
        loadEnvironment.LOGGER.info("After Script !!");
        System.out.println("After Script !!");
    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        loadEnvironment.LOGGER.error("Exception : "+throwable.toString());
        System.out.println("Exception : "+throwable.toString());
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
