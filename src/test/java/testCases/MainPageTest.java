package testCases;

import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.SignUpPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
//    chromedriver.exe
        System.setProperty("webdriver.gecko.driver", "D:\\IntelliJ IDEA Community Edition 2019.2\\IDEA projects\\testSelenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://github.com/");

        mainPage = new MainPage(driver, wait);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void integrationsHeaderPresenceTest() {
        Assert.assertEquals("Integrations", mainPage.getTextFromHeader());
    }

    @Test
    public void signUpTest(){
        SignUpPage signUpPage = mainPage.clickSignUpBtn();
        Assert.assertEquals("Create your account", signUpPage.getHeader());
        signUpPage.insertUserName("ivank");
        signUpPage.insertEmail("ivan@gmail.com");
        signUpPage.insertPassWord("1488GitHUB");
        Assert.assertEquals("Email is invalid or already taken", signUpPage.getEmailError());
        Assert.assertTrue(signUpPage.getUsernameError().contains("Username ivank is not available."));
    }

    @Test
    public void registerFailTest() {
        SignUpPage signUpPage = mainPage.registration("ivank", "ivan@gmail.com", "1488GitHUB");
        String error = signUpPage.getMainError();
        Assert.assertEquals("There were problems creating your account.", error);
    }

    @Test
    public void loginEmptyRegistrationTest(){
        SignUpPage signUpPage = mainPage.registration("", "ivan@gmail.com", "1488GitHUB");
        String userNameError = signUpPage.getUsernameError();
        String eMailError = signUpPage.getEmailError();
        String header = signUpPage.getHeader();
        Assert.assertEquals("Username can't be blank", userNameError);
        Assert.assertEquals("Email is invalid or already taken", eMailError);
        Assert.assertEquals("Create your account", header);
    }

    @Test
    public void createAnAccountTest(){
        LoginPage loginPage = mainPage.clickSignInBtn();
        Assert.assertEquals("Sign in to GitHub", loginPage.getHeadingText());
        SignUpPage signUpPage = loginPage.createAnAccount();
        Assert.assertEquals("Create your account", signUpPage.getHeader());

    }
}