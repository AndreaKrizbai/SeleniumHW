package com.automation.tests.homework1;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases1To5 {
    private RemoteWebDriver driver;

    @Test
    public void test1(){
        WebElement dob = driver.findElement(By.name("birthday"));
        dob.sendKeys("wrong_dob”");
        WebElement invalidDOB = driver.findElement(By.xpath("//small[@data-bv-result='INVALID']"));
        String expected = "The date of birth is not valid";
        Assert.assertEquals(invalidDOB.getText(), expected);
    }

    @Test
    public void test2(){

    }




    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        BrowserUtils.wait(2);
        WebElement regForm = driver.findElement(By.linkText("Registration Form"));
        driver.executeScript("arguments[0].scrollIntoView(true);",regForm);
        BrowserUtils.wait(2);
        regForm.click();
    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }
}
