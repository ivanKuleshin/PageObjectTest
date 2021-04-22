package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By signInButton = By.xpath("//a[@class='HeaderMenu-link no-underline mr-3']");
    private By signUpButton = By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/a[2]");
    private By userNameField = By.xpath("//input[@name='user[login]' and @id='user[login]']");
    private By eMailField = By.xpath("//input[@name='user[email]' and @id='user[email]']");
    private By passWordField = By.xpath("//input[@name='user[password]' and @id='user[password]']");
    private By SignUpForGitHubButton = By.xpath("(//button[text()='Sign up for GitHub'])[1]");
    private  By integrationsHeader = By.xpath("//h2[contains(text(), 'Integrations')]");

    public LoginPage clickSignInBtn(){
        wait.until(ExpectedConditions.presenceOfElementLocated(signInButton));
        driver.findElement(signInButton).click();
        return new LoginPage(driver, wait);
    }

    public SignUpPage clickSignUpBtn(){
        wait.until(ExpectedConditions.presenceOfElementLocated(signUpButton));
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver, wait);
    }

    private SignUpPage clickSignUpForGitHubButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(SignUpForGitHubButton));
        driver.findElement(SignUpForGitHubButton).click();
        return new SignUpPage(driver, wait);
    }

    private void insertUserName(String userName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(userNameField)).sendKeys(userName);
    }

    private void insertPassWord(String passWord){
        wait.until(ExpectedConditions.presenceOfElementLocated(passWordField));
        driver.findElement(passWordField).sendKeys(passWord);
    }

    private void insertEmail(String email){
        wait.until(ExpectedConditions.presenceOfElementLocated(eMailField));
        driver.findElement(eMailField).sendKeys(email);
    }

    public SignUpPage registration(String username, String email, String password){
        insertUserName(username);
        insertEmail(email);
        insertPassWord(password);
        return clickSignUpForGitHubButton();
    }

    public String getTextFromHeader() {
        wait.until(ExpectedConditions.presenceOfElementLocated(integrationsHeader));
        return driver.findElement(integrationsHeader).getText();
    }
}
