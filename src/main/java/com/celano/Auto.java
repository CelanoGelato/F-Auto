package com.celano;

import com.celano.config.DriverResourceLocator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Auto {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", new DriverResourceLocator().getChromeDriverLocation());
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        options.addArguments("disable-infobars");

        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);

//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1080, 750));
        driver.get("https://www.facebook.com/");
    }
}
