package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;

    SignUpPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By heading = By.xpath("//h1[text()='Create your account']");
    private By usernameField = By.xpath("//input[@name='user[login]']");
    private By emailField = By.xpath("//input[@name='user[email]']");
    private By passwordField = By.xpath("//input[@name='user[password]']");
    private By mainError = By.xpath("//form[@id='signup-form']/div[1]");
    private By usernameError = By.xpath("(//dd[@class='error'])[1]");
    private By emailError = By.xpath("(//dd[@class='error'])[2]");

    public void insertUserName(String userName) {
        driver.findElement(usernameField).sendKeys(userName);
    }

    public void insertPassWord(String passWord) {
        driver.findElement(passwordField).sendKeys(passWord);
    }

    public void insertEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public String getHeader() {
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(heading));
        return driver.findElement(heading).getText();
    }

    public String getMainError() {
        wait.until(ExpectedConditions.presenceOfElementLocated(mainError));
        return driver.findElement(mainError).getText();
    }

    public String getUsernameError() {
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameError));
        return driver.findElement(usernameError).getText();
    }

    public String getEmailError() {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailError));
        return driver.findElement(emailError).getText();
    }

}
