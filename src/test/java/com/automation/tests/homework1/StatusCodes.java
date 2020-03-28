package com.automation.tests.homework1;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StatusCodes {
   private RemoteWebDriver driver;

   @Test
   public void test09(){
       driver.findElement(By.linkText("200")).click();
       String expected = "This page returned a 200 status code.";
       String messageDisplayed = driver.findElement(By.tagName("p")).getText();
       Assert.assertTrue(messageDisplayed.contains(expected));
   }

    @Test
    public void test10(){
        driver.findElement(By.linkText("301")).click();
        String expected = "This page returned a 301 status code.";
        String messageDisplayed = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(messageDisplayed.contains(expected));
    }

    @Test
    public void test11(){
        driver.findElement(By.linkText("404")).click();
        String expected = "This page returned a 404 status code.";
        String messageDisplayed = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(messageDisplayed.contains(expected));
    }

    @Test
    public void test12(){
        driver.findElement(By.linkText("500")).click();
        String expected = "This page returned a 500 status code.";
        String messageDisplayed = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(messageDisplayed.contains(expected));
    }

    @BeforeMethod
    public void startup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Status Codes")).click();
        BrowserUtils.wait(2);
    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}
