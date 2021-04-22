package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By loginField = By.xpath("//input[@id='login_field']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By signInButton = By.xpath("//input[@type='submit']");
    private By heading = By.xpath("//h1[text()='Sign in to GitHub']");
    private By error = By.xpath("//div[@id='js-flash-container']//div[@class='container']");
    private By createAnAccountLink = By.xpath("//a[text()='Create an account']");

    private void insertUserName(String username){
        wait.until(ExpectedConditions.presenceOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys(username);
    }

    private void insertPassword(String password){
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    public void incorrectLogin(String username, String password){
        insertUserName(username);
        insertPassword(password);
        driver.findElement(signInButton).click();
        new LoginPage(driver, wait);
    }

    public String getHeadingText(){

        return driver.findElement(heading).getText();
    }

    public String getErrorText(){
        return driver.findElement(error).getText();
    }

    public SignUpPage createAnAccount(){
        wait.until(ExpectedConditions.presenceOfElementLocated(createAnAccountLink));
        driver.findElement(createAnAccountLink).click();
        return new SignUpPage(driver, wait);
    }
}
