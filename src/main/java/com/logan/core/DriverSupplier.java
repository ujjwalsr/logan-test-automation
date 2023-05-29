package com.logan.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSupplier {

    public static WebDriver driver;
    private final String browser = System.getProperty("browser");
    private final String appUrl = System.getProperty("loganAppUrl");

    public WebDriver getDriver() {
        return driver;
    }

    private static void chromeDriverSupplier() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
    }

    public void invokeApplication() {
        switch (browser) {
            case "chrome":
                chromeDriverSupplier();
                break;
            default:
                new Exception("Unsupported browser type");
        }

        getDriver().get(appUrl);
    }
}