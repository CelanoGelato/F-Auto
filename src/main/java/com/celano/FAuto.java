package com.celano;

import com.celano.config.DriverResourceLocator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class FAuto {
        private static final String URL = "https://www.facebook.com/";
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", new DriverResourceLocator().getChromeDriverLocation());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("--disable - gpu");

        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);

        driver.manage().window().setSize(new Dimension(1080, 750));
        F f = new F(driver);
        f.setAccount("toanmd2014@gmail.com");
        f.setPass("doantuoi185289");
        f.setUrl(URL);
        f.signIn();
    }
}
