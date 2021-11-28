package testCases.GoogleCloud;

import pages.GoogleCloud.GoogleCloudMainPage;
import pages.GoogleCloud.GoogleCloudPricingCalculatorPage;
import pages.TenMinEmail.TenMinEmailPage;
import org.testng.annotations.Test;
import setUp.BaseTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static driver.DriverProvider.getInstance;
import static org.assertj.core.api.Assertions.assertThat;

public class HardcoreTest extends BaseTest {
    private static final String PLATFORM_PRICING_CALCULATOR = "Google Cloud Platform Pricing Calculator";
    private static final String EXPECTED_CALCULATOR_URL = "https://cloud.google.com/products/calculator";
    private static final String PATTERN = "USD \\d+.\\d{2}";
    private static String originalTab = "";
    private static String secondTab = "";

    @Test
    public void hardcore() {
        new GoogleCloudMainPage(getInstance().getDriver()).openPage().searchForText(PLATFORM_PRICING_CALCULATOR).clickOnElementWithText(PLATFORM_PRICING_CALCULATOR);

        GoogleCloudPricingCalculatorPage pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(getInstance().getDriver());
        assertThat(pricingCalculatorPage.getPageUrl()).isEqualTo(EXPECTED_CALCULATOR_URL);

        String totalEstimatedCost = pricingCalculatorPage.fillTheFormWithStandardParameters().getTotalEstimatedCost();

        TenMinEmailPage tenMinEmailPage = new TenMinEmailPage(getInstance().getDriver());
        saveTemporaryEmailToClipboard(tenMinEmailPage);
        pricingCalculatorPage.sendTotalCostViaEmail();

        getInstance().getDriver().switchTo().window(secondTab);
        checkTotalCostFromEmailAndCalculator(tenMinEmailPage.getTotalCostInEmail(), totalEstimatedCost);
    }

    private void checkTotalCostFromEmailAndCalculator(String totalCostFromEmail, String totalCostFromCalculator){

        totalCostFromEmail = applyPatternToString(PATTERN, totalCostFromEmail);
        totalCostFromCalculator = applyPatternToString(PATTERN, totalCostFromCalculator);

        assertThat(totalCostFromEmail).isEqualTo(totalCostFromCalculator);
    }

    private String applyPatternToString(String regex, String myString){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(myString);
        if (matcher.find()) {
            myString = matcher.group();
        }
        return myString;
    }

    void saveTemporaryEmailToClipboard(TenMinEmailPage tenMinEmailPage){
        originalTab = getInstance().getDriver().getWindowHandle();
        tenMinEmailPage.openPageInNewTab().copyTemporaryEmail();
        secondTab = getInstance().getDriver().getWindowHandle();
        getInstance().getDriver().switchTo().window(originalTab);
    }
}
