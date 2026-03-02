package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setup() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--headless=new"); // optional: remove to see browser
    options.addArguments("--disable-gpu");

    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Updated to match your actual file
    String path = "file://" + System.getProperty("user.dir") + "/src/main/webapp/index.html";
    driver.get(path);
    }

    @Test
    void testAddition() {
        WebElement num1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("num1")));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement button = driver.findElement(By.tagName("button"));

        num1.sendKeys("10");
        num2.sendKeys("20");
        button.click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        // Remove "Sum is: " to match numeric output
        String output = result.getText().replace("Sum is: ", "").trim();

        assertEquals("30", output);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}