package com.automation.tests.homework1;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailingList {

    @Test
    public void SignUpTest() {
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tempmailaddress.com/");
        String tempEmail = driver.findElement(By.id("email")).getText();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        WebElement signUp = driver.findElement(By.linkText("Sign Up For Mailing List"));
        signUp.click();
        WebElement fullName = driver.findElement(By.name("full_name"));
        fullName.sendKeys("John Smith");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys(tempEmail);
        WebElement signUpButton = driver.findElement(By.name("wooden_spoon"));
        signUpButton.click();
        BrowserUtils.wait(2);

        String signUpMessage = driver.findElement(By.name("signup_message")).getText();
        Assert.assertEquals(signUpMessage, "Thank you for signing up. Click the button below to return to the home page.");
        BrowserUtils.wait(2);

        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().refresh();
        WebElement emailFromCT = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]"));
        String expected = "do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(emailFromCT.getText().trim(),expected);
        BrowserUtils.wait(2);

        emailFromCT.click();
        WebElement from = driver.findElement(By.id("odesilatel"));
        WebElement subject = driver.findElement(By.id("predmet"));
        Assert.assertEquals(from.getText(), expected);
        Assert.assertEquals(subject.getText(), "Thanks for subscribing to practice.cybertekschool.com!");

        BrowserUtils.wait(2);
        driver.quit();
    }










}
