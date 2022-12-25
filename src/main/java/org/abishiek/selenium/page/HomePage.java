package org.abishiek.selenium.page;

import org.abishiek.selenium.base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public static WebDriver driver;
    private String PROFILE_DROPDOWN = "xpath=>//span[@class='oxd-userdropdown-tab']";

    HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

}
