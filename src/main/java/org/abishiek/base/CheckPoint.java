package org.abishiek.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint {
    private static final Logger LOGGER = LogManager.getLogger(CheckPoint.class.getName());
    public static HashMap<String, String> resultMap = new HashMap<>();
    private static String PASS = "PASS";
    private static String FAIL = "FAIL";

    /***
     * Clears the results hash map
     */
    public static void clearHashMap() {
        LOGGER.info("Clearing Results Hash Map");
        resultMap.clear();
    }

    /***
     * Set status of the Result Map
     * @param mapKey - The method which fails along with message
     * @param status - The actual status
     */
    private static void setStatus(String mapKey, String status) {
        resultMap.put(mapKey, status);
        LOGGER.info(mapKey + " :-> " + resultMap.get(mapKey));
    }

    /**
     * Keeps the verification point status with testName, Result and Verification Point Message in hash map
     *
     * @param testName      - The test case name or testcase ID
     * @param result        - Verification Result from test method
     * @param resultMessage - Message tagged with verification
     */
    public static void mark(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            setStatus(mapKey, FAIL);
            LOGGER.error("Exception Occurred...", e);
            e.printStackTrace();
        }
    }

    /**
     * Keeps the verification point status with testName, Result and Verification Point Message in hash map
     * It asserts all the verifications in a test method, if any verification
     * in a test method is failed then the test case is failed
     *
     * @param testName      - The test case name or testcase ID
     * @param result        - Verification Result from test method
     * @param resultMessage - Message tagged with verification
     */
    public static void markFinal(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }

        ArrayList<String> resultList = new ArrayList<>();

        for (String key: resultMap.keySet()) {
            resultList.add(resultMap.get(key));
        }

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.contains(FAIL)) {
                LOGGER.error("Test Method Failed");
                Assert.fail();
            } else {
                LOGGER.info("Test Method Successful");
                Assert.assertTrue(true);
            }
        }
    }
}