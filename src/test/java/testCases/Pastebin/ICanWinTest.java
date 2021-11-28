package testCases.Pastebin;

import pages.Pastebin.PastebinMainPage;
import pages.Pastebin.PastebinResultPage;
import org.testng.annotations.Test;
import setUp.BaseTest;

import static driver.DriverProvider.getInstance;
import static org.assertj.core.api.Assertions.*;

public class ICanWinTest extends BaseTest {
    private static final String INPUT_TEXT = "Hello from WebDriver";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String TITLE_FOR_PASTE = "helloweb";
    private static final String EXPECTED_RESULT_PAGE_TITLE = TITLE_FOR_PASTE + " - Pastebin.com";

    @Test(testName = "First task. I can win!")
    public void iCanWinTest(){
        PastebinResultPage resultPage = new PastebinMainPage(getInstance().getDriver()).openPage()
                .enterDataToTextArea(INPUT_TEXT)
                .setPasteExpirationParameterToTenMinutes(EXPIRATION_TIME)
                .setTitleForPaste(TITLE_FOR_PASTE)
                .clickCreateNewPasteButton();

        assertThat(resultPage.getPageTitle()).as("Page titles are NOT matched").isEqualTo(EXPECTED_RESULT_PAGE_TITLE);
    }
}