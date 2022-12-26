package org.abishiek.base;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.abishiek.utilities.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomDriver {
    public WebDriver driver;
    private JavascriptExecutor js;

    public CustomDriver(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    /**
     * Refresh the current browser session
     */
    public void refresh() {
        driver.navigate().refresh();
        System.out.println("The Current Browser location was Refreshed...");
    }

    /**
     * @return Returns the Current Page Title
     */
    public String getTitle() {
        String title = driver.getTitle();
        System.out.println("Title of the page is :: " + title);
        return title;
    }

    /**
     * @return Current Browser URL
     */
    public String getURL() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL is :: " + url);
        return url;
    }

    /**
     * Navigate browser back
     */
    public void navigateBrowserBack() {
        driver.navigate().back();
        System.out.println("Navigate back");
    }

    /**
     * Navigate browser forward
     */
    public void navigateBrowserForward() {
        driver.navigate().back();
        System.out.println("Navigate back");
    }

    /***
     * Builds the By type with given locator strategy
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @return Returns By Type
     */
    public By getByType(String locator) {
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try {
            if (locatorType.contains("id")) {
                by = By.id(locator);
            } else if (locatorType.contains("name")) {
                by = By.name(locator);
            } else if (locatorType.contains("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.contains("css")) {
                by = By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                by = By.className(locator);
            } else if (locatorType.contains("tag")) {
                by = By.tagName(locator);
            } else if (locatorType.contains("link")) {
                by = By.linkText(locator);
            } else if (locatorType.contains("partiallink")) {
                by = By.partialLinkText(locator);
            } else {
                System.out.println("Locator type not supported");
            }
        } catch (Exception e) {
            System.out.println("By type not found with: " + locatorType);
        }
        return by;
    }

    /**
     * Builds The WebElement By given locator strategy
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return WebElement
     */
    public WebElement getElement(String locator, String info) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
        } catch (Exception e) {
            System.out.println("Element not found with: " + locator +" for "+ info);
            e.printStackTrace();
        }
        return element;
    }

    /***
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return elementList - List of WebElements are returned
     */
    public List<WebElement> getElementList(String locator, String info) {
        List<WebElement> elementList = new ArrayList<>();
        By byType = getByType(locator);
        try {
            elementList = driver.findElements(byType);
            System.out.println("Element List found with: " + locator + " " + info);
        } catch (Exception e) {
            System.out.println("Element List not found with: " + locator + " " + info);
            e.printStackTrace();
        }
        return elementList;
    }

    /***
     * Check if element is present
     * @param locator locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @return boolean if element is present or not
     */
    public boolean isElementPresent(String locator, String info) {
        List<WebElement> elementList = getElementList(locator, info);
        int size = elementList.size();
        return size > 0;
    }

    /**
     * Click element with information about element and
     * time to wait in seconds after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
    public void elementClick(WebElement element, String info, long timeToWait) {
        try {
            element.click();
            if (timeToWait == 0) {
                System.out.println("Clicked On :: " + info);
            } else {
                Util.sleep(timeToWait, "Clicked on :: " + info);
            }
        } catch (Exception e) {
            System.out.println("Cannot click on :: " + info);
            takeScreenshot("Click ERROR", "");
        }
    }

    /**
     * Click element with no time to wait after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
    public void elementClick(WebElement element, String info) {
        elementClick(element, info, 0);
    }

    /**
     * Click element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - information about element
     * @param timeToWait - wait time for click
     */
    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = this.getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    /**
     * Click element with locator and no time to wait
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element
     */
    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }

    /***
     * Click element using JavaScript
     * @param element - WebElement to perform javascript Click operation
     *
     * @param info - Information about element
     */
    public void javascriptClick(WebElement element, String info) {
        try {
            js.executeScript("arguments[0].click();", element);
            System.out.println("Clicked on :: " + info);
        } catch (Exception e) {
            System.out.println("Cannot click on :: " + info);
        }
    }

    /***
     * Click element using JavaScript
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element
     */
    public void javascriptClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        javascriptClick(element,info);
    }

    /***
     * Click element when element is clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public void clickWhenReady(By locator, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            WebElement element;
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Element clicked on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
    }

    /***
     * Send Keys to element
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            //Util.sleep(1000, "Waiting Before Entering Data");
            element.sendKeys(data);
            System.out.println("Send Keys on element :: "
                    + info + " with data :: " + data);
        } catch (Exception e) {
            System.out.println("Cannot send keys on element :: "
                    + info + " with data :: " + data);
        }
    }

    /***
     * Send Keys to element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = this.getElement(locator, info);
        sendData(element, data, info, clear);
    }

    /***
     * Send Keys to element with clear
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(WebElement element, String data, String info) {
        sendData(element, data, info, true);
    }

    /***
     * Send Keys to element with locator and clear
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    /**
     * Get text of a web element
     *
     * @param element - WebElement to perform click action
     * @param info    - Information about element
     */
    public String getText(WebElement element, String info) {
        System.out.println("Getting Text on element :: " + info);
        String text;
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
            System.out.println(" The text is : " + text);
        } else {
            text = "NOT_FOUND";
        }
        return text.trim();
    }

    /**
     * Get text of a web element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return Returns the value of attribute
     */
    public String getText(String locator, String info) {
        WebElement element = this.getElement(locator, info);
        return this.getText(element, info);
    }

    /**
     * Check if element is enabled
     * @param element  - the element for which is to be checked if enabled
     * @param info - Information about element, usually text on element
     * @return Returns the status true if the element is enabled
     */
    public Boolean isEnabled(WebElement element, String info) {
        boolean enabled = false;
        if (element != null) {
            enabled = element.isEnabled();
            if (enabled)
                System.out.println("Element :: " + info + " is Enabled");
            else
                System.out.println("Element :: " + info + " is Disabled");
        }
        return enabled;
    }

    /***
     * Check if element is enabled with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return Returns true if the element is enabled
     */
    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    /**
     * Check if element is displayed
     * @param element The element which is to be checked if displayed
     * @param info - Information about element, usually text on element
     * @return Returns true if the element is displayed
     */
    public Boolean isDisplayed(WebElement element, String info) {
        boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                System.out.println("Element :: " + info + " is displayed");
            else
                System.out.println("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    /***
     * Check if element is displayed with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return Returns true if the element is displayed
     */
    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    /**
     * @param element - The element which is to be checked if selected
     * @param info - Information about element, usually text on element
     * @return Returns true if the element is selected
     */
    public Boolean isSelected(WebElement element, String info) {
        boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                System.out.println("Element :: " + info + " is selected");
            else
                System.out.println("Element :: " + info + " is already selected");
        }
        return selected;
    }

    /**
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return Returns true if the element is selecteds
     */
    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    /**
     * Selects a check box irrespective of its state
     *
     * @param element The checkbox element to be checked
     * @param info - Information about element, usually text on element
     */
    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is checked");
        } else
            System.out.println("Element :: " + info + " is already checked");
    }

    /**
     * Selects a check box irrespective of its state, using locator
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     */
    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    /**
     * UnSelects a check box irrespective of its state
     *
     * @param element - The element to be unchecked
     * @param info - Information about element, usually text on element
     */
    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is unchecked");
        } else
            System.out.println("Element :: " + info + " is already unchecked");
    }

    /**
     * UnSelects a check box irrespective of its state, using locator
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     */
    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    /**
     * @param element element to be submitted
     * @param info - Information about element, usually text on element
     * @return returns true if submit is successful
     */
    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            System.out.println("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    /**
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return returns true if submit is successful
     */
    public Boolean Submit(String locator, String info) {
       WebElement element= getElement(locator,info);
       return Submit(element,info);
    }

    /**
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     * @param attribute - The attribute of the element to be determined
     * @return Returns the value of attribute
     */
    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    /**
     * @param element The element for which attribute is to be found
     * @param attribute - The attribute of the element to be determined
     * @return Returns the value of attribute
     */
    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - The time required to wait for element to appear
     * @return Returns WebElement after waiting for the specific timeout
     */
    public WebElement waitForElement(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            System.out.println("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return element;
    }

    /***
     * Wait for element to be clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public WebElement waitForElementToBeClickable(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            System.out.println("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return element;
    }

    /**
     *
     */
    public boolean waitForLoading(String locator, long timeout) {
        By byType = getByType(locator);
        boolean elementInvisible = false;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            elementInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(byType));
            System.out.println("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return elementInvisible;
    }

    /**
     * Mouse Hovers to an element
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *      *                tag=>example, xpath=>//example, link=>example
     */
    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    /**
     * @param element -Element for which the dropdown value to be selected
     * @param optionToSelect - The option to be selected
     */
    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        System.out.println("Selected option : " + optionToSelect);
    }

    /**
     * Selects a given option in list box
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *      *                tag=>example, xpath=>//example, link=>example
     * @param optionToSelect - The option to be selected
     */
    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    /**
     * get Selected drop down value
     *
     * @param element - Element for which the selected dropdown is to be retrived
     * @return Returns the selected element.
     */
    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }

    /**
     * @param element -
     * @param optionToVerify - check whether the particular dropdown value exists or not
     */
    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (WebElement webElement : optList) {
            String text = getText(webElement, "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Selected Option : " + optionToVerify + " exist");
        } else {
            System.out.println("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }

    /***
     *
     * @param methodName - the method in which the test scenario failed
     * @param browserName - the browser in which the test scenario failed
     * @return path - the path can be used in the reports
     */
    public String takeScreenshot(String methodName, String browserName) {
        String fileName = Util.getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        //noinspection ResultOfMethodCallIgnored
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("Screen Shot Was Stored at: "+ path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        System.out.println("Double Clicked on :: " + info);
        action.perform();
    }

    /**
     * Right Click a WebElement
     *
     * @param locator  - locator strategy, id=>example, name=>example, css=>#example,
     *      *      *                tag=>example, xpath=>//example, link=>example
     */
    public void rightClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        System.out.println("Double Clicked on :: " + info);
    }

    /**
     * Right-click a WebElement and select the option
     *
     * @param elementLocator - the element to be right-clicked
     * @param itemLocator - the option to be clicked
     */
    public void selectItemRightClick(String elementLocator, String itemLocator) {
        WebElement element = getElement(elementLocator, "info");
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        WebElement itemElement = getElement(itemLocator, "info");
        elementClick(itemElement, "Selected Item");
    }

    /**
     * @param key - the key to be pressed
     */
    public void keyPress(Keys key, String info) {
        Actions action = new Actions(driver);
        action.keyDown(key).build().perform();
        System.out.println("Key Pressed :: " + info);
    }
}