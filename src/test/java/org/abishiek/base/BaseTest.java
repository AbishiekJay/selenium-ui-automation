package org.abishiek.base;

import org.abishiek.page.HomePage;
import org.abishiek.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    private static WebDriver driver;
    private HomePage home;
    private LoginPage login;
    private String configPath = System.getProperty("user.dir")+"src/main/java/org/abishiek/selenium/config/config.properties";

    public BaseTest(){
        try{
            Properties prop = new Properties();
            FileInputStream inputStream = new FileInputStream(configPath);
            prop.load(inputStream);
        }catch (FileNotFoundException file){
            file.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp(){

    }
}
