package org.abishiek.selenium.page;

import org.abishiek.selenium.base.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public WebDriver driver;
    private String USERNAME_FIELD = "xpath=>//input[@name='username']";
    private String PASSWORD_FIELD = "xpath=>//input[@name='password']";
    private String LOGIN_BUTTON = "xpath=>//button[@type='submit']";
    LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }



    public void loginToHomePage(){

    }
}
