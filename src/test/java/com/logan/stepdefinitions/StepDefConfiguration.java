package com.logan.stepdefinitions;

import com.logan.core.PageManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class StepDefConfiguration {

    @Before(value = "@gui", order = 0)
    public void initPages() {
        PageManager.getInstance().initialisePageGenerator();
    }

    @After(order = 0, value = "@gui")
    public void clean(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(this.takeScreenshot(), "image/png", scenario.getName());
        }
        PageManager.getInstance().closeDriver();
        PageManager.cleanUp();
    }

    private byte[] takeScreenshot() {
        WebDriver driver = PageManager.getInstance().getPageGenerator().driver;
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}