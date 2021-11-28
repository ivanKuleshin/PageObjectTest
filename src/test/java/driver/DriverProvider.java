package driver;

import org.openqa.selenium.WebDriver;

public class DriverProvider {
    private static WebDriver driver;
    private static DriverProvider instance;

    private DriverProvider() {
        driver = WebDriverFactory.getWebDriver(System.getProperty("browser"));
        driver.manage().deleteAllCookies();
    }

    public static DriverProvider getInstance() {
        if (instance == null) {
            instance = new DriverProvider();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
