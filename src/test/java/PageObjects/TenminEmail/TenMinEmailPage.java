package PageObjects.TenminEmail;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TenMinEmailPage {
    private final WebDriver driver;
    private final WebDriverWait explicitWait;

    private static final String HOME_PAGE = "https://10minutemail.com/";

    @FindBy(xpath = "//h2[contains(text(), 'Estimated Monthly Cost:')]")
    private WebElement totalCostFromEmail;

    @FindBy(xpath = "//span[contains(text(), 'Google Cloud Platform Price Estimate')]")
    private WebElement letterSubject;

    @FindBy(xpath = "//div[@id = 'copy_address']/span")
    private WebElement copyEmailButton;

    public TenMinEmailPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 120);
        PageFactory.initElements(driver, this);
    }

    public TenMinEmailPage openPageInNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        driver.get(HOME_PAGE);

        return this;
    }

    public void copyTemporaryEmail() {
        copyEmailButton.click();
    }

    public String getTotalCostInEmail() {
        driver.navigate().refresh();
        explicitWait.until(ExpectedConditions.visibilityOf(letterSubject)).click();

        return totalCostFromEmail.getText();
    }
}