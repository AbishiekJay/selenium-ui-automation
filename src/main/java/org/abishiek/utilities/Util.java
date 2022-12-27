package org.abishiek.utilities;

import com.google.common.collect.Ordering;
import org.abishiek.base.CustomDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Util {
    private static final Logger LOGGER = LogManager.getLogger(Util.class.getName());
    private static String configPath = System.getProperty("user.dir") + "/src/main/java/org/abishiek/config/config.properties";

    /***
     * Sleep for specified number of milliseconds
     * @param msec - Milliseconds for wait
     * @param info - description for sleep
     */
    public static void sleep(long msec, String info) {
        if (info != null) {
            LOGGER.info("Waiting " + (msec * .001) + " seconds :: " + info);
        }
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec - Milliseconds for wait
     */
    public static void sleep(long msec) {
        sleep(msec, null);
    }

    /***
     * Get Random number within specified range
     * @param min - Minimum value (lower bound)
     * @param max - Maximum value (upper bound)
     * @return a random number
     */
    public static int getRandomNumber(int min, int max) {
        int diff = max - min;
        int randomNum = (int)(min + Math.random() * diff);
        LOGGER.info("Random Number :: " + randomNum +
                " within range :: " + min + " and :: " + max);
        return randomNum;
    }

    /***
     * Get Random number within specified range
     * @param number - Maximum value (upper bound)
     * @return a random number
     */
    public static int getRandomNumber(int number) {
        return getRandomNumber(1, number);
    }

    /***
     * Get random unique string with specified length
     * @param length - Length of the string
     * @return a unique string
     */
    public static String getRandomString(int length) {
        StringBuilder sbuilder = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i<length; i++) {
            int index = (int) (Math.random() * chars.length());
            sbuilder.append(chars.charAt(index));
        }
        String randomString = sbuilder.toString();
        LOGGER.info("Random string with length :: "
                + length + " is :: " + randomString);
        return randomString;
    }

    /***
     * Get random unique string with 10 characters length
     * @return a unique string
     */
    public static String getRandomString() {
        return getRandomString(10);
    }

    /***
     * Get simple date as string in the specified format
     * @param format MMddyy MMddyyyy
     * @return date as string type
     */
    public static String getSimpleDateFormat(String format){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        LOGGER.info("Date with format :: "
                + format + " :: " + formattedDate);
        return formattedDate;
    }

    /***
     * Get simple date time as string
     * @return date time as string type
     */
    public static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        LOGGER.info("Date and Time :: " + date);
        return date;
    }

    /**
     * Checks whether actual String contains expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be checked with actual text
     * @return boolean result
     */
    public static boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())){
            LOGGER.info("Actual Text From Web Application UI   --> : "+ actualText);
            LOGGER.info("Expected Text From Web Application UI --> : "+ expText);
            LOGGER.info("### Verification Contains !!!");
            return true;
        }
        else{
            LOGGER.info("Actual Text From Web Application UI   --> : "+ actualText);
            LOGGER.info("Expected Text From Web Application UI --> : "+ expText);
            LOGGER.error("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    /**
     * Checks whether actual string matches with expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be matched with actual text
     * @return Returns true if the text matches exactly else false
     */
    public static boolean verifyTextMatch(String actualText, String expText) {
        if (actualText.equals(expText)){
            LOGGER.info("Actual Text From Web Application UI   --> : "+ actualText);
            LOGGER.info("Expected Text From Web Application UI --> : "+ expText);
            LOGGER.info("### Verification MATCHED !!!");
            return true;
        }else{
            LOGGER.info("Actual Text From Web Application UI   --> : "+ actualText);
            LOGGER.info("Expected Text From Web Application UI --> : "+ expText);
            LOGGER.error("### Verification DOES NOT MATCH !!!");
            return false;
        }
    }

    /**
     * Verify actual list contains items of the expected list
     *
     * @param actList - The actual list retrieved
     * @param expList - The expected list
     * @return Returns true if actual list contains all the values of expected list, else false
     */
    public static Boolean verifyListContains(List<String> actList, List<String> expList) {
        for (String s : expList) {
            if (!actList.contains(s)) {
                return false;
            }
        }
        LOGGER.info("Actual List Contains Expected List !!!");
        return true;
    }

    /***
     * Verify actual list matches expected list with re-arranged position
     * @param actList - The actual list retrieved
     * @param expList - The expected list
     * @return Returns true if both the lists match, else false
     */
    public static Boolean verifyListMatchRandIndex(List<String> actList, List<String> expList) {
        boolean found = false;
        int actListSize = actList.size();
        int expListSize = expList.size();
        if (actListSize != expListSize) {
            return false;
        }
        expList.removeAll(actList);
        found = expList.isEmpty();
        if (found) {
            LOGGER.info("Actual List Matches Expected List !!!");
            return true;
        }
        else {
            LOGGER.error("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    /**
     * Verifies item is present in the list
     * @param actList - The list in which the search is to be carried out
     * @param item - The item to be searched
     * @return boolean result
     */
    public static Boolean verifyItemPresentInList(List<String> actList, String item){
        int actListSize = actList.size();
        for (int i = 0; i < actListSize; i++) {
            if (!actList.contains(item)) {
                LOGGER.error("Item is NOT present in List !!!");
                return false;
            }
        }
        LOGGER.info("Item is present in List !!!");
        return true;
    }

    /**
     * Verify if list is in ascending order
     * @param list - List
     * @return boolean result
     */
    public static boolean isListAscendingOrder(List<Long> list){
        return Ordering.natural().isOrdered(list);
    }

    /**
     *
     * @param methodName - The method from which the failure occurred
     * @param browserName - The browser in which the failure occurred
     * @return Returns the String combining methodName and browserName along-with date and time
     */
    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(browserName)
                                                .append("_")
                                                .append(methodName)
                                                .append("_")
                                                .append(localDateTime)
                                                .append(".png");
        return name.toString();
    }

    /**
     * To store values in run time
     * @param parameterName -The parameter to be stored in config file
     * @param parameterValue- The value of parameter to be stored in config file
     */
    public static void saveConfigParameter(String parameterName, String parameterValue){
        Properties prop = new Properties();
        InputStream fileIn;
        try {
            fileIn = new FileInputStream(configPath);
            prop.load(fileIn);
            prop.setProperty(parameterName,parameterValue);
            OutputStream out = new FileOutputStream(configPath);
            prop.store(out,"");
        }catch (IOException e){
            LOGGER.error(e);
        }
    }
    public static String getConfigParameter(String parameterName){
        Properties prop = new Properties();
        InputStream fileIn;
        try {
            fileIn = new FileInputStream(configPath);
            prop.load(fileIn);
        } catch (IOException e){
            LOGGER.error(e);
        }
        return prop.getProperty(parameterName);
    }

}