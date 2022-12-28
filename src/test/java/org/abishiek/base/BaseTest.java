package org.abishiek.base;

import org.abishiek.page.HomePage;
import org.abishiek.page.LoginPage;
import org.abishiek.utilities.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class.getName());

    public WebDriver driver;
    protected HomePage home;
    protected LoginPage login;
    Properties prop;

    public BaseTest(){

        try{
            prop = new Properties();
            String configPath = System.getProperty("user.dir") + "/src/main/java/org/abishiek/config/config.properties";
            FileInputStream inputStream = new FileInputStream(configPath);
            prop.load(inputStream);
        } catch (IOException file){
            file.printStackTrace();

        }
    }

    @BeforeClass
    public void setUp(){
        driver = WebDriverFactory.getInstance().getDriver(prop.getProperty("browser"));
        driver.get(prop.getProperty("url"));
        login = new LoginPage(driver);
        login.loginToHomePage(Util.getConfigParameter("Username"),Util.getConfigParameter("Password"));
        login.checkSuccessfulLogin();
    }

    @BeforeMethod
   public void startReport(Method method){
        String testName = method.getAnnotation(Test.class).testName();
        LOGGER.info("========== The test for " + testName + " has begun ==========");
        CheckPoint.clearHashMap();
    }

    @AfterMethod
    public void endReport(Method method){
        String testName = method.getAnnotation(Test.class).testName();
        LOGGER.info("========== The test for " + testName + " has ended ==========\n\n");
    }
    @AfterClass
    public void tearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }
}
