package com.logan.stepdefinitions;

import com.logan.core.PageManager;
import com.logan.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

    private final PageManager pageManager = PageManager.getInstance();
    private LoginPage loginPage = this.pageManager.getPageGenerator().getInstance(LoginPage.class);

    @Given("I have user credentials")
    public void iHaveUserCredentials() {
        System.out.println("get data");
    }

    @When("I enter username and password")
    public void iEnterUsernameAndPassword() {
        System.out.println("get data");
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        this.loginPage.loginToApplication();
    }
}