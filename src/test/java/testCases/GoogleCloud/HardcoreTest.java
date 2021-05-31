package testCases.GoogleCloud;

import PageObjects.GoogleCloud.GoogleCloudMainPage;
import PageObjects.GoogleCloud.GoogleCloudPricingCalculatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import setUp.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HardcoreTest extends BaseTest {
    private static final String PLATFORM_PRICING_CALCULATOR = "Google Cloud Platform Pricing Calculator";
    private static final String EXPECTED_CALCULATOR_URL = "https://cloud.google.com/products/calculator";
    private static final String TEN_MIN_EMAIL_URL = "https://10minutemail.com/";

    @Test
    public void hardcore() {
        new GoogleCloudMainPage(driver).openPage().searchForText(PLATFORM_PRICING_CALCULATOR).clickOnElementWithText(PLATFORM_PRICING_CALCULATOR);

        GoogleCloudPricingCalculatorPage pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(driver);
        assertThat(pricingCalculatorPage.getPageUrl()).isEqualTo(EXPECTED_CALCULATOR_URL);

        String totalEstimatedCost = pricingCalculatorPage.fillTheFormWithStandardParameters().getTotalEstimatedCost();

        saveTemporaryEmailToClipboard();

        pricingCalculatorPage.sendTotalCostViaEmail();
    }

    void saveTemporaryEmailToClipboard(){
        openNewTab();

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        driver.get(TEN_MIN_EMAIL_URL);

        driver.findElement(By.xpath("//div[@id = 'copy_address']")).click();

        driver.switchTo().window(tabs.stream().findFirst().orElseThrow());
    }

    void openNewTab(){
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }
}
