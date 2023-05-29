package com.logan.pages;

import com.logan.core.DriverSupplier;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BasePage {

    protected FluentWait<WebDriver> waitUntil() {
        return waitUntil(20);
    }

    protected FluentWait<WebDriver> waitUntil(long seconds) {
        return this.waitUntil(Duration.ofSeconds(seconds), Duration.ofSeconds(1));
    }

    public FluentWait<WebDriver> waitUntil(Duration timeout, Duration polling) {
        return new FluentWait<>(DriverSupplier.driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);
    }

    protected void click(WebElement element) {
        waitUntil().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void writeText(WebElement element, String text) {
        if(StringUtils.isEmpty(text)) {
            return;
        }
        waitUntil().until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        waitUntil().until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
        DriverSupplier.driver.findElement((By.cssSelector("body"))).click();
    }
}
