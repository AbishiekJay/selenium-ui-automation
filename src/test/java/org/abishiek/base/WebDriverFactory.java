package org.abishiek.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;

public class WebDriverFactory {
    private static final WebDriverFactory instance = new WebDriverFactory();
    private WebDriverFactory(){

    }
    public static WebDriverFactory getInstance(){
        return instance;
    }
    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<>();
    public WebDriver getDriver(String browser){
        WebDriver driver;
        if(threadedDriver.get()==null){
            try{
                if(browser.equalsIgnoreCase("chrome")){
                    ChromeOptions options = setChromeOptions();
                    driver = new ChromeDriver(options);
                    threadedDriver.set(driver);
                }if(browser.equalsIgnoreCase("firefox")){
                    FirefoxOptions options = setFFOptions();
                    driver = new FirefoxDriver(options);
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
    /***
     * Set Chrome Options
     * @return options
     */
    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }

    /***
     * Set Firefox Options
     * @return options
     */
    private FirefoxOptions setFFOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, false);
        return options;
    }

}
