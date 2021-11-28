package pages.Pastebin;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.*;

public class PastebinMainPage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private final WebDriverWait explicitWait;
    private final WebDriver driver;

    private static final String LOCATOR_FOR_EXPIRATION_ELEMENT = "//ul[@id = 'select2-postform-expiration-results']/li[text() = '%s']";
    private static final String LOCATOR_FOR_SYNTAX_HIGHLIGHT = "//ul[@class = 'select2-results__options select2-results__options--nested']/li[text() = '%s']";

    @FindBy(id = "postform-text")
    private WebElement textArea;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationSelect;

    @FindBy(id = "select2-postform-format-container")
    private WebElement syntaxHighlightSelect;

    @FindBy(id = "postform-name")
    private WebElement titleForPaste;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement createNewPasteButton;

    public PastebinMainPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public PastebinMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        waitForVisibilityOfElement(textArea);
        return this;
    }

    public PastebinMainPage enterDataToTextArea(String text) {
        textArea.sendKeys(text);
        return this;
    }

    public PastebinMainPage setPasteExpirationParameterToTenMinutes(String expectedPasteExpirationParameter) {
        this.pasteExpirationSelect.click();
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(String.format(LOCATOR_FOR_EXPIRATION_ELEMENT, expectedPasteExpirationParameter))))
                .click();

        assertThat(pasteExpirationSelect.getText())
                .as("Paste Expiration parameter was NOT applied")
                .isEqualTo(expectedPasteExpirationParameter);
        return this;
    }

    public PastebinMainPage setSyntaxHighlightToBash(String expectedSyntaxHighlight) {
        waitForVisibilityOfElement(syntaxHighlightSelect).click();
        explicitWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(String.format(LOCATOR_FOR_SYNTAX_HIGHLIGHT, expectedSyntaxHighlight))))
                .click();

        assertThat(syntaxHighlightSelect.getText()).as("").isEqualTo(expectedSyntaxHighlight);
        return this;
    }

    public PastebinMainPage setTitleForPaste(String title) {
        waitForVisibilityOfElement(titleForPaste).sendKeys(title);
        return this;
    }

    public PastebinResultPage clickCreateNewPasteButton() {
        this.createNewPasteButton.submit();
        return new PastebinResultPage(driver);
    }

    private WebElement waitForVisibilityOfElement(WebElement element) {
        try {
            return explicitWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element was NOT found on the page during timeout");
        }
    }
}
