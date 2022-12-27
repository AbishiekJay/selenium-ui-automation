package org.abishiek.page;

import org.abishiek.base.CheckPoint;
import org.abishiek.base.CustomDriver;
import org.abishiek.utilities.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SideMenu extends CustomDriver {
    public WebDriver driver;
    private String SIDEMENU_ITEMS = "xpath=>//li[@class='oxd-main-menu-item-wrapper']/child::a/child::span";
    private String SIDEMENU_COLLAPSE = "xpath=>//i[contains(@class,'bi-chevron-left')]";
    private String SIDEMENU_EXPAND = "xpath=>//i[contains(@class,'bi-chevron-left')]";

    public SideMenu(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void checkSideMenuOptions(List<String> expectedSideMenuOptions){
        Util.sleep(3000);
        boolean collapsed = isElementPresent(SIDEMENU_EXPAND,"Check if sidemenu collapsed");
        if(collapsed)
            elementClick(SIDEMENU_EXPAND,"click to expand sidemenu");
        List<WebElement> elementList = getElementList(SIDEMENU_ITEMS,"get menu items");
        List<String> actualSideMenuOptions = new ArrayList<>();
        for(WebElement element : elementList)
            actualSideMenuOptions.add(getText(element,"side menu option"));
        CheckPoint.markFinal("TC00003",
                Util.verifyListMatchRandIndex(actualSideMenuOptions,expectedSideMenuOptions),
                "Verify all side menu options are present");
    }
    //class="oxd-icon bi-chevron-left"
}
