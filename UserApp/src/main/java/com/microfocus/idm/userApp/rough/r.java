package com.microfocus.idm.userApp.rough;

import com.microfocus.idm.userApp.base.uiBase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

/**
 * Created by user on 4/9/2017.
 */
public class r extends uiBase {

    public r(EventFiringWebDriver driver) {
        super(driver);
    }

    public void executeA () {
        File envFile = new File(System.getProperty("user.dir")+"/src/main/resources/Properties/"+"abc.xml");
        SAXReader reader = new SAXReader();
        try {
            Document env = reader.read(envFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Node node = env.selectSingleNode("Environment/Setup/Server/IP");
        System.out.println(node.getText());
        driver.get("http://www.google.com");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeB () {
        driver.get("http://www.facebook.com");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
