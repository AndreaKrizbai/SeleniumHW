package com.automation.tests.homework1;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Autocomplete {

    @Test
    public void autocompleteTest(){
        WebDriverManager.chromedriver().version("79").setup();
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        BrowserUtils.wait(2);

        String message = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(message, "You selected: United States of America");

        BrowserUtils.wait(2);
        driver.quit();
    }
}
