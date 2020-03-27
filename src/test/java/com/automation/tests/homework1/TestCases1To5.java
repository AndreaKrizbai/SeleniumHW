package com.automation.tests.homework1;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
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

    @Test
    public void test5(){
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("John");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Smith");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("johnsmith");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("johnsmith@gmail.com");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("JohnSmithPassword");
        WebElement phoneNumber = driver.findElement(By.name("phone"));
        phoneNumber.sendKeys("240-123-4567");
        WebElement maleRadioButton = driver.findElement(By.cssSelector("input[value='male']"));
        maleRadioButton.click();
        WebElement dob = driver.findElement(By.name("birthday"));
        dob.sendKeys("03/27/2000");
        driver.executeScript("window.scrollBy(0, 250)");
        Select department = new Select(driver.findElement(By.name("department")));
        department.selectByVisibleText("Department of Agriculture");
        Select jobTitle = new Select(driver.findElement(By.name("job_title")));
        jobTitle.selectByVisibleText("Project Manager");
        WebElement javaButton = driver.findElement(By.id("inlineCheckbox2"));
        javaButton.click();
        WebElement signUpButton = driver.findElement(By.id("wooden_spoon"));
        signUpButton.click();
        BrowserUtils.wait(2);
        WebElement actual = driver.findElement(By.xpath("//p"));
        Assert.assertEquals(actual.getText(), "You've successfully completed registration!");
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
