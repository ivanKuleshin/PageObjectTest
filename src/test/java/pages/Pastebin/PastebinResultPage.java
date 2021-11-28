package pages.Pastebin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PastebinResultPage {
    private final WebDriver driver;
    WebDriverWait explicitWait;

    @FindBy(xpath = "//div[contains(text(), 'RAW Paste Data')]")
    private WebElement rawPasteData;

    @FindBy(xpath = "//ol[@class = 'bash']/li/div/span[contains(text(), 'git')]")
    private List<WebElement> bashElements;

    @FindBy(xpath = "//textarea[@class = 'textarea']")
    private WebElement textArea;

    public PastebinResultPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle(){
        explicitWait.until(ExpectedConditions.visibilityOf(rawPasteData));
        return driver.getTitle();
    }

    public List<WebElement> getBashElements() {
        return bashElements;
    }

    public String getTextFromTextArea(){
        return explicitWait.until(ExpectedConditions.visibilityOf(textArea)).getText();
    }
}
