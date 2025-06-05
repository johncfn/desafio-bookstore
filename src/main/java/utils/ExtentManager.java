package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-report/ExtentReport.html");
            reporter.config().setReportName("Relatório de Testes Automatizados");
            reporter.config().setDocumentTitle("Desafio Pitang - QA");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
