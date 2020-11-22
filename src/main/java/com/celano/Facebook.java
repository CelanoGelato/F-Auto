package com.celano;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Facebook {
    private WebDriver webDriver;
    private ObjectMapper objectMapper;
    private String account;
    private String pass;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Facebook(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.objectMapper = new ObjectMapper();
    }

    public void loginWithAccount() {
        this.webDriver.get(url);
        //Username
        WebElement emailElement = new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailElement.clear();
        emailElement.sendKeys(this.account);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //Password
        WebElement passElement = new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("pass")));
        passElement.clear();
        passElement.sendKeys(this.pass);
        this.webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //Click Login
        WebElement loginElement = new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("u_0_b")));
        loginElement.click();
//        this.webDriver.switchTo().alert().dismiss();
    }

    public boolean isLoginSuccess() {
        try {
            new WebDriverWait(this.webDriver, 20)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='navigation']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveCookies() {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\cookie.data");
        try {
            file.delete();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Cookie ck : this.webDriver.manage().getCookies()) {
                bufferedWriter.write(ck.getName() + "|" + ck.getValue() + "|" + ck.getDomain() + "|" + ck.getPath() + "|" + ck.getExpiry() + "|" + ck.isSecure());
                bufferedWriter.newLine();
            }
//            String s = this.objectMapper.writeValueAsString(this.webDriver.manage().getCookies());
//            bufferedWriter.write(s);

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginWithCookies() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\cookie.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            webDriver.get(this.url);
            String strline;
            while ((strline = bufferedReader.readLine()) != null) {
//            if (bufferedReader.readLine() != null) {
//                CookiesUser[] cookiesUsers = this.objectMapper.readValue(bufferedReader.readLine(), CookiesUser[]);
//            }
                String[] token = strline.split("\\|");

//                while (token.hasMoreTokens()) {
                String name = token[0];
                String value = token[1];
                String domain = token[2];
                String path = token[3];
                Date expiry = null;
                String val;
                if (!(val = token[4]).equals("null")) {
                    expiry = sdf.parse(val);
                }
                boolean isSecure = Boolean.parseBoolean(token[5]);
                Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                System.out.println(ck);
//                    for (CookiesUser cookiesUser : cookiesUsers) {
//                    }
                this.webDriver.manage().addCookie(ck);
//                }
            }
            webDriver.get(this.url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signIn() {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\cookie.data");
        if (file.exists()) {
            this.loginWithCookies();
        } else {
            this.loginWithAccount();
        }
        if (isLoginSuccess()) {
            this.saveCookies();
        }
    }
}
