package com.logan.core;

import com.logan.constants.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverSupplier {

    private final String browser = System.getProperty("browser");
    private final String appUrl = System.getProperty("loganAppUrl");
    private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();
    public WebDriver driver;
    public DriverType driverType;

    public WebDriver initializeDriver() {
        invokeApplication();
        return driver;
    }

    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--verbose");
        options.addArguments("--no-sandbox");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        WebDriverManager.chromedriver().setup();

        return (WebDriver) new ChromeDriver(options);
    };

    static {
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
    }

    public void invokeApplication() {
        switch (browser) {
            case "chrome":
                driverType = DriverType.CHROME;
                break;
            default:
                throw new WebDriverException("Unsupported browser type");
        }

        driver = driverMap.get(driverType).get();
        driver.get(appUrl);
    }
}