package com.logan.stepdefinitions;

import com.logan.core.DriverSupplier;
import com.logan.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

    private LoginPage loginPage = new LoginPage();

    @Given("I have user credentials")
    public void iHaveUserCredentials() {
        new DriverSupplier().invokeApplication();
    }

    @When("I enter username and password")
    public void iEnterUsernameAndPassword() {
        System.out.println("get data");
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        loginPage.loginToApplication();
    }


}
