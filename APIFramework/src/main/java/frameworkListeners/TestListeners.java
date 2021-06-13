/*
 *
 */
package frameworkListeners;

import frameworkBase.TestBotBase;
import io.qameta.allure.Attachment;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class TestListeners.
 *
 * @author PIPULPANT
 */
public class TestListeners extends TestBotBase implements ITestListener, ISuiteListener, IInvokedMethodListener, IReporter {


    /**
     * Gets the test method name.
     *
     * @param iTestResult the i test result
     * @return the test method name
     */
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }


    /**
     * Save text log.
     *
     * @param message the message
     * @return the string
     */
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }


    /* (non-Javadoc)
     * @see org.testng.ISuiteListener#onStart(org.testng.ISuite)
     * This method is invoked before the SuiteRunner starts.
     */
    @Override
    public void onStart(ISuite arg0) {
        // TODO Auto-generated method stub
        Reporter.log("About to begin executing Suite " + arg0.getName(), true);

    }

    /* (non-Javadoc)
     * This method is invoked after the SuiteRunner has run all the test suites.
     * @see org.testng.ISuiteListener#onFinish(org.testng.ISuite)
     */
    @Override
    public void onFinish(ISuite arg0) {
        // TODO Auto-generated method stub
        Reporter.log("About to end executing Suite " + arg0.getName(), true);

    }

    /* (non-Javadoc)
     * Invoked after the test class is instantiated and before any configuration method is called.
     * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
     */
    @Override
    public void onStart(ITestContext iTestContext) {

        // iTestContext.setAttribute("WebDriver", getDriverWithListeners());
        Reporter.log("About to begin executing Test " + iTestContext.getName(), true);

    }

    /* (non-Javadoc)
     * Invoked after all the tests have run and all their Configuration methods have been called.
     * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
     */
    @Override
    public void onFinish(ITestContext iTestContext) {

        Reporter.log("Completed executing test " + iTestContext.getName(), true);

    }

    /* (non-Javadoc)
     * Invoked each time before a test will be invoked.
     * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");

    }

    /* (non-Javadoc)
     * Invoked each time a test succeeds.
     * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        // This is calling the printTestResults method
        printTestResults(iTestResult);
    }

    /* (non-Javadoc)
     * Invoked each time a test fails.
     * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        // This is calling the printTestResults method
        printTestResults(iTestResult);


        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " test got failed!");

    }

    /* (non-Javadoc)
     * Invoked each time a test is skipped.
     * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        printTestResults(iTestResult);
    }

    /* (non-Javadoc)
     * Invoked each time a method fails but has been annotated with successPercentage and this failure still keeps it within the success percentage requested.
     * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }


    // This is the method which will be executed in case of test pass or fail
    // This will provide the information on the test

    /**
     * Prints the test results.
     *
     * @param result the result
     */
    private void printTestResults(ITestResult result) {

        Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

        if (result.getParameters().length != 0) {

            String params = null;

            for (Object parameter : result.getParameters()) {

                params += parameter.toString() + ",";

            }

            Reporter.log("Test Method had the following parameters : " + params, true);

        }

        String status = null;

        switch (result.getStatus()) {

            case ITestResult.SUCCESS:

                status = "Pass";

                break;

            case ITestResult.FAILURE:

                status = "Failed";

                break;

            case ITestResult.SKIP:

                status = "Skipped";

        }

        Reporter.log("Test Status: " + status, true);

    }

    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test

    /* (non-Javadoc)
     * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod, org.testng.ITestResult)
     */
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

        String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

        Reporter.log(textMsg, true);

    }

    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test

    /* (non-Javadoc)
     * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod, org.testng.ITestResult)
     */
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

        String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

        Reporter.log(textMsg, true);

    }

    // This will return method names to the calling function

    /**
     * Return method name.
     *
     * @param method the method
     * @return the string
     */
    private String returnMethodName(ITestNGMethod method) {

        return method.getRealClass().getSimpleName() + "." + method.getMethodName();

    }


    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // TODO Auto-generated method stub


    }


}

