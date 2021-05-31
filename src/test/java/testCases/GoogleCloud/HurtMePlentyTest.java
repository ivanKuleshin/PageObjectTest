package testCases.GoogleCloud;

import PageObjects.GoogleCloud.GoogleCloudMainPage;
import PageObjects.GoogleCloud.GoogleCloudPricingCalculatorPage;
import org.testng.annotations.Test;
import setUp.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;

public class HurtMePlentyTest extends BaseTest {
    private static final String TEXT_FOR_SEARCH = "Google Cloud Platform Pricing Calculator";
    private static final String EXPECTED_CALCULATOR_URL = "https://cloud.google.com/products/calculator";
    private static final String EXPECTED_TOT_ESTIMATED_COST = "Total Estimated Cost: USD 195.67 per 1 month";
    private static final String EXPECTED_VM_CLASS = "VM class: regular";
    private static final String EXPECTED_REGION = "Region: Iowa";

    @Test
    public void hurtMePlenty() {
        new GoogleCloudMainPage(driver).openPage().searchForText(TEXT_FOR_SEARCH).clickOnElementWithText(TEXT_FOR_SEARCH);
        GoogleCloudPricingCalculatorPage pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(driver);

        assertThat(pricingCalculatorPage.getPageUrl()).isEqualTo(EXPECTED_CALCULATOR_URL);

        String totalEstimatedCost = pricingCalculatorPage.fillTheFormWithStandardParameters().getTotalEstimatedCost();

        assertThat(totalEstimatedCost).isEqualTo(EXPECTED_TOT_ESTIMATED_COST);
        assertThat(pricingCalculatorPage.getVMCLassResult()).isEqualTo(EXPECTED_VM_CLASS);
        assertThat(pricingCalculatorPage.getRegionLabel()).isEqualTo(EXPECTED_REGION);
    }
}
