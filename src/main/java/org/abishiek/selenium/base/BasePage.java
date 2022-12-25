package org.abishiek.selenium.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
    public WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }
    public boolean verifyTitle(String expectedTitle){
        return driver.getTitle().equalsIgnoreCase(expectedTitle);
    }
}
