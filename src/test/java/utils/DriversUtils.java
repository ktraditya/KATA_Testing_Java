package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriversUtils {
    static WebDriver driver;
    public static String fs = File.separator;
    public static Properties configProperties;
    

    public static void initDriver() {
    	
    	String chromeDriverPath = "src" + fs + "test" + fs + "resources" + fs + "Drivers" + fs + "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {

        if (driver == null ) {
            initDriver();
        }
        return driver;
    }

    public static void tearDown() {
        driver.quit();
        driver = null;
    }
}

