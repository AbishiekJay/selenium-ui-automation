package org.abishiek.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance(){
        if(extent == null){
           extent = createInstance();
        }
        return extent;
    }
    public static synchronized ExtentReports createInstance(){
        String fileName =Util.getReportName();
        String reportDirectory = System.getProperty("user.dir")+Util.getConfigParameter("reportPath");
        new File(reportDirectory).mkdirs();
        String path = reportDirectory + fileName;
        ExtentSparkReporter sparkHtmlReporter = new ExtentSparkReporter(path);
        sparkHtmlReporter.config().setTheme(Theme.STANDARD);
        sparkHtmlReporter.config().setDocumentTitle("Automation Run");
        sparkHtmlReporter.config().setEncoding("utf-8");
        sparkHtmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization", "Sample Project");
        extent.setSystemInfo("Automation Framework", "Selenium WebDriver");
        extent.attachReporter(sparkHtmlReporter);
        return extent;
    }

}
