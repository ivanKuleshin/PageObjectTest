package setUp;

import driver.DriverProvider;
import org.testng.annotations.AfterSuite;

public class BaseTest {
    @AfterSuite
    public void tearDown() {
        DriverProvider.getInstance().getDriver().quit();
    }
}
