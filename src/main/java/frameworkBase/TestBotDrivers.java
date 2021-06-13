package frameworkBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// TODO: Auto-generated Javadoc
/**
 * The Class TestBotDrivers.
 * @author Pipul Pant
 */
public class TestBotDrivers {

    /** The driver. */
    private static WebDriver driver;

    public static WebDriver setChromeDriver()
    {
        //NEED TO CHANGE LATER TO HUB FOR DYNAMIC DRIVER INITIALISATION
        System.setProperty("webdriver.chrome.driver","/Users/patron/Desktop/APITestFramework/chromedriver");
        ChromeOptions options = new ChromeOptions();//SETTING CHROME DRIVER
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");//RUNNING IN HEADLESS MODE
        driver = new ChromeDriver(options);//CREATING Chrome Driver Object
        return driver;
    }


}