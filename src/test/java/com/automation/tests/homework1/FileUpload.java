package com.automation.tests.homework1;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUpload {

    @Test
    public void uploadTest(){
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(2);

        WebElement chooseFileButton = driver.findElement(By.id("file-upload"));
        chooseFileButton.sendKeys(System.getProperty("user.dir")+"/textFile.txt");
        BrowserUtils.wait(2);

        driver.findElement(By.id("file-submit")).click();
        WebElement message = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(message.getText(), "File Uploaded!");

        driver.findElement(By.id("uploaded-files")).isDisplayed();






        BrowserUtils.wait(2);
        driver.quit();
    }

}
