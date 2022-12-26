package org.abishiek.TestClass;

import org.abishiek.base.BaseTest;
import org.abishiek.dataProvider.DataProviderClass;
import org.abishiek.page.HomePage;
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
    public void checkDropDown(Map<String,String> data){
        System.out.println(data);
        Util.sleep(3000);
        home=new HomePage(driver);
        List<String> dropDownList = new ArrayList<>(data.values());
        home.checkProfileDropdownElements(dropDownList);

    }
}
