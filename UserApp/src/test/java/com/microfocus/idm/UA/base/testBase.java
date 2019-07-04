package com.microfocus.idm.UA.base;

import com.microfocus.idm.userApp.utils.commonUtils;
import com.microfocus.idm.userApp.utils.eventHandler;
import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.dom4j.DocumentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.asserts.SoftAssert;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sushant on 3/10/2017.
 */
public class testBase {// implements IHookable{

    private static WebDriver wDriver;
    public static EventFiringWebDriver driver;
    public SoftAssert asert;

    public testBase() {}

    public void launchBrowser(String browser) {
        if (driver == null) {
            try {
                loadEnvironment.loadProperties();
            } catch (DocumentException e) {
                e.printStackTrace();
            }


            // ----------------- Google Chrome + SSL -------------------

            /*DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome ();
            handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            wDriver = new ChromeDriver(handlSSLErr);

            driver = new EventFiringWebDriver(wDriver);
            eventListener eListener = new eventListener();
            driver.register(eListener);*/

            // ----------------- Microsoft Edge + SSL-------------------

            /*DesiredCapabilities handlSSLErr = DesiredCapabilities.edge();
            handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
            System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
            driver = new EdgeDriver(handlSSLErr);
            driver.navigate().to("javascript:document.getElementById('overridelink').click()");*/


            // ----------------- Local Execution -------------------

            /*if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                wDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                wDriver = new ChromeDriver();
            }*/

            // ----------------- Selenium grid + Parallel ---------------

            if (browser.equalsIgnoreCase("firefox")) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                //capabilities.setCapability("platform", Platform.WIN10);
                String nodeURL = commonUtils.makeNodeURL();
                try {
                    wDriver = new RemoteWebDriver(new URL(nodeURL), capabilities);
                    ((RemoteWebDriver) wDriver).setFileDetector(new LocalFileDetector());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (browser.equalsIgnoreCase("chrome")) {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                //capabilities.setCapability("platform", Platform.LINUX);
                String nodeURL = commonUtils.makeNodeURL();
                try {
                    wDriver = new RemoteWebDriver(new URL(nodeURL), capabilities);
                    ((RemoteWebDriver) wDriver).setFileDetector(new LocalFileDetector());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            // -------- This is to use the Selenium Webdriver Event Listener

            driver = new EventFiringWebDriver(wDriver);
            eventHandler handler = new eventHandler();
            driver.register(handler);
        }
    }

    /*public testBase() {
        if (driver == null) {
            try {
                loadEnvironment.loadProperties();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            *//*DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-incognito");

            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setPlatform(Platform.WIN10);
            capabilities.setBrowserName("chrome");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");*//*

            if(browser.equalsIgnoreCase("firefox")) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                String nodeURL = commonUtils.makeNodeURL();
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
                    ((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if(browser.equalsIgnoreCase("firefox")) {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                String nodeURL = commonUtils.makeNodeURL();
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
                    ((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            *//*System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();*//*

        }
    }*/

    /*public void setUp(String config_file, String environment) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
        JSONObject envs = (JSONObject) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("key");
        }

        if(capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true"){
            l = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            l.start(options);
        }

        driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);
    }*/


    /*@Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    public byte[] takeScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }*/

    /*public static WebDriver getDriver() {
        return wDriver;
    }*/

    public static WebDriver getDriver() {
        return driver;
    }

    public void assertString(String actual, String expected) {
        asert = new SoftAssert();
        asert.assertEquals(actual, expected);
        asert.assertAll();
    }

    public void assertInt(int actual, int expected) {
        asert = new SoftAssert();
        asert.assertEquals(actual, expected);
        asert.assertAll();
    }

    public void assertBoolean(boolean actual, boolean expected) {
        asert = new SoftAssert();
        asert.assertEquals(actual, expected);
        asert.assertAll();
    }

    /*public void takeScreenshotOnFailure(ITestResult result, testBase t) {
        if(ITestResult.FAILURE==result.getStatus())
            screen.captureScreenshot(driver,t.getClass().getName());
    }
    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if(ITestResult.FAILURE==result.getStatus())
            screen.captureScreenshot(driver,"t1");
    } */

    @AfterClass
    public void quit() {
        /*LogEntries logs = driver.manage().logs().get("browser");
        System.out.println(logs);*/
        //wDriver.quit();
        driver.quit();

    }
}
