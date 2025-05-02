package Utility;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ReportManager {
    public static ExtentReports createReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/extentReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Najaf Haider");
        return extent;
    }
}
