package PageObjects.GoogleCloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.stream.Stream;

public class GoogleCloudPricingCalculatorPage{
    private final WebDriverWait explicitWait;
    private final WebDriver driver;

    private static final String NUMBER_OF_INSTANCES = "4";

    @FindBy(xpath = "//md-tab-item/div[@title = 'Compute Engine']")
    private WebElement computeEngineIcon;

    @FindBy(id = "input_65")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "(//div[contains(text(), 'Free: Debian, CentOS')])[1]")
    private WebElement defaultOCValueLocator;

    @FindBy(xpath = "//md-select-value//div[contains(text(), 'Free: Debian, CentOS')]")
    private WebElement defaultOCValueLocatorInList;

    @FindBy(xpath = "(//div[text() = 'Regular'])[1]")
    private WebElement defaultVWClassValue;

    @FindBy(xpath = "(//div[text() = 'Regular'])[1]")
    private WebElement defaultVWClassValueInList;

    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']/div/button[contains(text(), 'Add to Estimate')]")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//*[@id = 'compute']/following-sibling::h2/b")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//md-list-item[@role = 'listitem']/div[contains(text(), 'VM class')]")
    private WebElement VMCLassResult;

    @FindBy(xpath = "//md-list-item[@role = 'listitem']/div[contains(text(), 'Region')]")
    private WebElement regionLabel;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

//    public GoogleCloudPricingCalculatorPage selectComputeEngineOption(){
//        driver.switchTo().frame(mainIFrame);
//        explicitWait.until(ExpectedConditions.visibilityOf(computeEngineIcon)).click();
//        return this;
//    }

    public GoogleCloudPricingCalculatorPage fillTheFormWithStandardParameters(){
        driver.switchTo().frame(explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[1]"))));
        driver.switchTo().frame("myFrame");

        // umber of instances
        explicitWait.until(ExpectedConditions.visibilityOf(numberOfInstancesInput)).sendKeys(NUMBER_OF_INSTANCES);

        // Operating System
        defaultOCValueLocator.click();
        Actions action = new Actions(driver);
        action.moveToElement(defaultOCValueLocatorInList).click().build().perform();

        // VW CLass
        defaultVWClassValue.click();
        action.moveToElement(defaultVWClassValueInList).click().build().perform();

        // Confirm the form
        addToEstimateButton.submit();

        return this;
    }

    public String getTotalEstimatedCost() {
        return String.join("", Arrays.asList(totalEstimatedCost.getText()
                .replaceAll(" +", " ")
                .split("\n"))).trim();
    }

    public String getVMCLassResult(){
        return VMCLassResult.getText().trim();
    }

    public String getRegionLabel(){
        return regionLabel.getText().trim();
    }
}
