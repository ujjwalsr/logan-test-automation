package com.logan.pages;

import com.logan.core.PageGenerator;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends PageGenerator {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    public FluentWait<WebDriver> waitUntil(Duration timeout, Duration polling) {
        return new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);
    }

    protected FluentWait<WebDriver> waitUntil() {
        return waitUntil(20);
    }

    protected FluentWait<WebDriver> waitUntil(long seconds) {
        return this.waitUntil(Duration.ofSeconds(seconds), Duration.ofSeconds(1));
    }

    protected boolean waitForPageToReload(long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    JavascriptExecutor jsExecutor = null;
                    jsExecutor = (JavascriptExecutor) driver;
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for JavaScript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    JavascriptExecutor jsExecutor = null;
                    jsExecutor = (JavascriptExecutor) driver;
                    return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
                } catch (Exception e) {
                    return true;
                }
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    protected void clearText(WebElement element) {
        waitUntil().until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }

    protected void click(WebElement element) {
        waitUntil().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        this.waitForPageToReload(20);
    }

    protected void writeText(WebElement element, String text) {
        if (StringUtils.isEmpty(text)) {
            return;
        }
        element.clear();
        element.sendKeys(text);
        super.driver.findElement((By.cssSelector("body"))).click();
    }
}