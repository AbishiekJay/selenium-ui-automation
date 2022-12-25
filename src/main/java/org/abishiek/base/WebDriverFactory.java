package org.abishiek.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverFactory {
    private static final WebDriverFactory instance = new WebDriverFactory();
    private WebDriverFactory(){

    }
    public static WebDriverFactory getInstance(){
        return instance;
    }
    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
    public WebDriver getDriver(String browser){
        WebDriver driver;
        if(threadedDriver.get()==null){
            try{
                if(browser.equalsIgnoreCase("chrome")){
                    driver = new ChromeDriver();
                    threadedDriver.set(driver);
                }if(browser.equalsIgnoreCase("firefox")){
                    driver = new FirefoxDriver();
                    threadedDriver.set(driver);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }
    public void quitDriver(){
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }
}
