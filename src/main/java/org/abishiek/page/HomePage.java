package org.abishiek.page;

import org.abishiek.base.BasePage;
import org.abishiek.base.CheckPoint;
import org.abishiek.utilities.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public WebDriver driver;
    private String PROFILE_DROPDOWN = "xpath=>//span[@class='oxd-userdropdown-tab']";
    private String DROPDOWN_ELEMENTS ="xpath=>//a[@role='menuitem']";
    private String DROPDOWN_ABOUT = "xpath=>//a[text()='About']";
    private String DROPDOWN_ABOUT_CLOSE = "xpath=>//div[@role='document']/child::button";
    private String DROPDOWN_SUPPORT = "xpath=>//a[text()='Support']";
    private String DROPDOWN_CHANGE_PASSWORD = "xpath=>//a[text()='Change Password']";
    private String UPDATE_PASSWORD_TEXT = "xpath=>//hr[@role='separator']/preceding-sibling::h6";

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void checkProfileDropdownElements(List<String> expectedDropdownLabels){
        elementClick(PROFILE_DROPDOWN,"profile dropdown");
        List<WebElement> actualDropdownList = getElementList(DROPDOWN_ELEMENTS,"list of dropdown elements");
        List<String> actualDropdownLabels = new ArrayList<>();
        for(WebElement element : actualDropdownList)
            actualDropdownLabels.add(getText(element, "label value of dropdown"));
        CheckPoint.markFinal("Verify Dropdown",
                Util.verifyListMatchRandIndex(expectedDropdownLabels,actualDropdownLabels),
                "The elements of the list is validated");
    }
    public void clickAboutOption(){
        elementClick(PROFILE_DROPDOWN,"profile dropdown");
        elementClick(DROPDOWN_ABOUT,"Select About from dropdown");
        Util.sleep(3000);
        elementClick(DROPDOWN_ABOUT_CLOSE,"close the about section");
    }
    public void clickSupportOption(String expectedURL){
        elementClick(PROFILE_DROPDOWN,"profile dropdown");
        elementClick(DROPDOWN_SUPPORT,"Select Support from dropdown");
        Util.sleep(3000);
        String actualURL = getURL();
        CheckPoint.markFinal("TC00002",Util.verifyTextMatch(actualURL,expectedURL),"Check Support URL");
    }
    public void clickChangePassword(String expectedURL){
        elementClick(PROFILE_DROPDOWN,"profile dropdown");
        elementClick(DROPDOWN_CHANGE_PASSWORD,"Select Support from dropdown");
        Util.sleep(3000);
        CheckPoint.mark("TC00002",Util.verifyTextMatch(getURL(),expectedURL),"Check Change Password URL");
        CheckPoint.markFinal("TC00002",
                Util.verifyTextMatch(getText(UPDATE_PASSWORD_TEXT,"get text of update password"),"Update Password"),"Verify Update Password Present");
    }
}
