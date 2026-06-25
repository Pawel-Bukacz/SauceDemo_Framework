package utils;

import io.qameta.allure.Step;
import org.testng.*;

import static org.testng.Reporter.log;


public class Retry implements IRetryAnalyzer {

    private int count = 1;
    private static final int MAX_RETRY = 2;

    @Override
    @Step("Rerunning the test.")
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < MAX_RETRY) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                log("Retrying once again");
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}