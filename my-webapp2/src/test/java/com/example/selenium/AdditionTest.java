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

    // Toggle this to true if you want headless mode
    boolean headless = false;

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // avoid CORS issues

        if (headless) {
            options.addArguments("--headless=new"); // run in background
            options.addArguments("--disable-gpu");
        }

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Dynamic file path for your local index.html
        String path = "file://" + System.getProperty("user.dir") + "/src/main/webapp/index.html";
        driver.get(path);
    }

    @Test
    void testAddition() {
        // Wait until input fields are visible
        WebElement num1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("num1")));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement button = driver.findElement(By.tagName("button"));

        // Input numbers and click
        num1.sendKeys("10");
        num2.sendKeys("20");
        button.click();

        // Wait for result element
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        // Remove text prefix to match numeric assertion
        String output = result.getText().replace("Sum is: ", "").trim();

        assertEquals("30", output);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
