package pages.GoogleCloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudSearchResultPage {
    private final WebDriverWait explicitWait;
    private final WebDriver driver;

    private static final String locatorForElementWithText = "//div[@class = 'gs-title']/a/b[text() = '%s']";

    public GoogleCloudSearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void clickOnElementWithText(String text){
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(String.format(locatorForElementWithText, text))))
                .click();
    }
}
