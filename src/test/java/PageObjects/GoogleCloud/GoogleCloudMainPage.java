package PageObjects.GoogleCloud;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudMainPage {
    private static final String HOME_PAGE = "https://cloud.google.com/";

    private final WebDriverWait explicitWait;
    private final WebDriver driver;

    @FindBy(xpath = "//input[@name = 'q']")
    private WebElement searchField;

    public GoogleCloudMainPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudMainPage openPage(){
        driver.get(HOME_PAGE);
        explicitWait.until(ExpectedConditions.visibilityOf(searchField));
        return this;
    }

    public GoogleCloudSearchResultPage searchForText(String text) {
        explicitWait.until(ExpectedConditions.visibilityOf(searchField)).click();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
        return new GoogleCloudSearchResultPage(driver);
    }
}
