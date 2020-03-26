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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<WebElement>progLangs = driver.findElements(By.xpath("//label[@class='form-check-label']"));
        List<String> expected = new ArrayList<>();
        expected.add("C++");
        expected.add("Java");
        expected.add("JavaScript");
        Assert.assertEquals(BrowserUtils.getTextFromWebElements(progLangs), expected);
    }

    @Test
    public void test3(){
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("a");
        String expected = "first name must be more than 2 and less than 64 characters long";
        WebElement actual = driver.findElement(By.xpath("//small[@data-bv-validator='stringLength']"));
        Assert.assertEquals(actual.getText(), expected);
    }

    @Test
    public void test4(){
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("a");
        //String expected = "The last name must be more than 2 and less than 64 characters long";
        WebElement actual = driver.findElement(By.xpath("//small[@data-bv-for='lastname'][2]"));
       //Assert.assertEquals(actual.getText(), expected);
        Assert.assertTrue(actual.isDisplayed());
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
