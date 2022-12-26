package org.abishiek.page;

import org.abishiek.base.BasePage;
import org.abishiek.base.CheckPoint;
import org.abishiek.utilities.Util;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public WebDriver driver;
    private String USERNAME_FIELD = "xpath=>//input[@name='username']";
    private String PASSWORD_FIELD = "xpath=>//input[@name='password']";
    private String LOGIN_BUTTON = "xpath=>//button[@type='submit']";
    private String USER_IMAGE = "class=>oxd-userdropdown-img";
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /**
     *
     * @param username - Username to login
     * @param password - Password to login
     */
    public void loginToHomePage(String username, String password){
        sendData(USERNAME_FIELD,username,"send username");
        sendData(PASSWORD_FIELD,password,"send password");
        Submit(LOGIN_BUTTON,"Login details");
        Util.sleep(5000);
    }
    public void checkSuccessfulLogin(){
        boolean successfulLogin = isElementPresent(USER_IMAGE,"Check user image");
        CheckPoint.markFinal("LoginCheck",successfulLogin,"login check");
    }
}
