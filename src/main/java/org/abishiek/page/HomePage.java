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
        System.out.println(actualDropdownLabels);
        System.out.println(expectedDropdownLabels);
        CheckPoint.markFinal("Verify Dropdown",
                Util.verifyListMatchRandIndex(expectedDropdownLabels,actualDropdownLabels),
                "The elements of the list is validated");
    }

}
