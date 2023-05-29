package com.logan.core;

import com.logan.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageGenerator {
    public WebDriver driver;

    public PageGenerator(WebDriver driver) {
        this.driver = driver;
    }

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }
}

