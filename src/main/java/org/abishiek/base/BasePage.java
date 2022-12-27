package org.abishiek.base;

import org.abishiek.utilities.Util;
import org.openqa.selenium.WebDriver;

public class BasePage extends CustomDriver{
    public WebDriver driver;
    private String TOASTER_CLOSE ="class=>oxd-toast-close-container";
    private String TOASTER_STATUS_MESSAGE = "xpath=>//div[@class='oxd-toast-start']/child::div/following-sibling::div/child::p[contains(@class,'oxd-text--toast-title')]";
    public BasePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public boolean verifyTitle(String expectedTitle){
        return driver.getTitle().equalsIgnoreCase(expectedTitle);
    }

    public void checkToasterMessage(String statusMessage){
        CheckPoint.markFinal("COMMON_TOASTER",
                Util.verifyTextMatch(getText(TOASTER_STATUS_MESSAGE,"Get toaster Message"),statusMessage),
                "Verify the expexted toaster");
    }

    public void closeToasterMessage(){
        elementClick(TOASTER_CLOSE,"Close Toaster");
    }
}
