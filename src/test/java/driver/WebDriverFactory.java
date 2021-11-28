package driver;

import driver.managers.ChromeDriverManager;
import driver.managers.FireFoxDriverManager;
import org.openqa.selenium.WebDriver;


public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserName) {
        WebDriver driver;
        if (null != browserName) {
            if ("firefox".equals(browserName)) {
                driver = new FireFoxDriverManager().getDriver();
            } else {
                driver = new ChromeDriverManager().getDriver();
            }
        } else {
            throw new RuntimeException("Please specify correct browser name! Current value is null.");
        }
        return driver;
    }
}
