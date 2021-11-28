package driver.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeDriverManager implements WebDriverManager {

    @Override
    public WebDriver createDriver() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
