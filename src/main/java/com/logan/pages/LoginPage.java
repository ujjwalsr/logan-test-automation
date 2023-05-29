package com.logan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    private final String userName = System.getProperty("user");
    private final String password = System.getProperty("password");

    @FindBy(how = How.ID, using = "exampleInputEmail1")
    private WebElement emailAddressTxt;
    @FindBy(how = How.ID, using = "exampleInputPassword1")
    private WebElement passwordTxt;
    @FindBy(how = How.ID, using = "logid1")
    private WebElement loginBtn;

    public void loginToApplication() {
        super.writeText(this.emailAddressTxt, userName);
        super.writeText(this.passwordTxt, password);
        super.click(loginBtn);
    }

}
