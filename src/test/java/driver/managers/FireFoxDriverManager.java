package driver.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FireFoxDriverManager implements WebDriverManager {
    @Override
    public WebDriver createDriver() {
        io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
