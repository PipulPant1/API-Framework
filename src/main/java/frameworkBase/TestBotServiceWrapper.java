package frameworkBase;


import frameworkUtils.LocalStorage;
import frameworkUtils.Log;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.LinkedHashMap;
import java.util.Random;


// TODO: Auto-generated Javadoc

/**
 * The Class TestBotServiceWrapper.
 *
 * @author PipulPant
 */

public class TestBotServiceWrapper extends TestBotBase {
    /**
     * This method is used to setup new user session
     *
     * @param //baseURL is the base uri of the API
     * @param //userRow is the column from where we read the user data
     * @throws Exception
     */
    public static void setNewUserSession(String user, String password) throws Exception {

        WebDriver driver = TestBotDrivers.setChromeDriver();

        //driver.get(url)
        driver.get("http://auto-stage.admin.fuseclassroom.com/");

        Thread.sleep(8000);
        WebElement username_CSS = driver.findElement(By.cssSelector("#username"));
        username_CSS.sendKeys(user);//username
        WebElement Password_CSS = driver.findElement(By.cssSelector("#password"));
        Password_CSS.sendKeys(password);//password
        WebElement login_CSS = driver.findElement(By.xpath("//button[@class='btn btn-md btn-green w-100 font-weight-semibold']"));

        login_CSS.click();//login to fuse machines portal

        Thread.sleep(10000);//waiting for 10s for cookies to read

        //accessing local storage method
        LocalStorage localStorage = new LocalStorage(driver);
        String access_token = localStorage.getItemFromLocalStorage("access_token");//accessing the access token
        String idToken = localStorage.getItemFromLocalStorage("id_token");//accessing the token id

        //Setting Access Token and Token Id in the POJO Class
        TestBotBase.setAccess_Token(access_token);
        TestBotBase.setToken_id(idToken);

        //Logging the header information
        Log.info("Bearer Token is:" + access_token + "  Token ID is: " + idToken);
        getAuthData = new LinkedHashMap<>();
        getAuthData.put("Authorization", "bearer " + TestBotBase.getAccess_Token());
        getAuthData.put("idToken", TestBotBase.getToken_id());
        getAuthData.put("Origin", "https://auto-stage.admin.fuseclassroom.com");
        getAuthData.put("Host", "app-api-stage.fuseclassroom.com");
        getAuthData.put("Content-Type", "application/json");

    }

    /**
     * Custom assertion method for string with allure reporting integration for results
     *
     * @param expected    expected string
     * @param actual      actual string from the response
     * @param description used for assertion of string with allure step for reporting
     */
    public static void assertionEquals(String expected, String actual, String description) {
        Allure.step("Assertion :" + description);
        SoftAssert softAssert = new SoftAssert();
        Allure.step("Expected output : " + expected);
        Allure.step(" Actual output :" + actual);
        softAssert.assertEquals(actual, expected, description);
        softAssert.assertAll();
    }

    /**
     * Custom assertion method for boolean with allure reporting integration integration for results
     *
     * @param expected    expected boolean(true/false)
     * @param actual      actual boolean from the response
     * @param description used for assertion of boolean with allure step for reporting
     */
    public static void assertionEquals(Boolean expected, Boolean actual, String description) {
        Allure.step("Assertion :" + description);
        SoftAssert softAssert = new SoftAssert();
        Allure.step("Expected output : " + expected);
        Allure.step(" Actual output :" + actual);
        softAssert.assertEquals(actual, expected, description);
        softAssert.assertAll();
    }

    public static String random_Text() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }


}
