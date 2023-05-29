package com.logan.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.logan.stepdefinitions",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber-report.json",
                "json:target/cucumber-reports/cucumber-report.json"
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@test",
        stepNotifications = true
)
public class TestRunner {
}
