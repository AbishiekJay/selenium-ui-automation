package org.abishiek.TestClass;

import org.abishiek.base.BaseTest;
import org.abishiek.dataProvider.DataProviderClass;
import org.abishiek.page.HomePage;
import org.abishiek.page.SideMenu;
import org.abishiek.utilities.Util;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePageTest extends BaseTest {
    @DataProvider(name = "excelData")
    public Object[][] getDataForTest(Method methodName){
        String testName = methodName.getAnnotation(Test.class).testName();
        String sheetName = testName.split(",")[0];
        return DataProviderClass.dataSupplier(sheetName);
    }
    @Test(testName = "TC00001",priority = 1,dataProvider = "excelData")
    public void checkDropDown(Map<String , String> data){
        Util.sleep(3000);
        home=new HomePage(driver);
        List<String> dropDownList = new ArrayList<>(data.values());
        home.checkProfileDropdownElements(dropDownList);
    }
    @Test(testName = "TC00002",priority = 2, dataProvider = "excelData")
    public void clickDropDownElements(Map<String , String> data){
        home.clickAboutOption();
        home.clickSupportOption(data.get("SUPPORT_URL"));
        home.clickChangePassword(data.get("CHANGE_PASSWORD_URL"));
    }
    @Test(testName = "TC00003" , priority = 3, dataProvider = "excelData")
    public void checkSideMenu(Map<String,String> data){
        SideMenu menu = new SideMenu(driver);
        List<String> sideMenuList = new ArrayList<>(data.values());
        menu.checkSideMenuOptions(sideMenuList);
    }
}
