package testCases.Pastebin;

import pages.Pastebin.PastebinMainPage;
import pages.Pastebin.PastebinResultPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;
import setUp.BaseTest;

import static driver.DriverProvider.getInstance;
import static org.assertj.core.api.Assertions.assertThat;

public class BringItOutTest extends BaseTest {
    private static final String INPUT_TEXT =
            "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String EXPECTED_SYNTAX_HIGHLIGHT = "Bash";
    private static final String TITLE_FOR_PASTE = "how to gain dominance among developers";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String EXPECTED_RESULT_PAGE_TITLE = TITLE_FOR_PASTE + " - Pastebin.com";
    private static final String PINK_COLOR = "#b9ca4a";

    @Test(testName = "Second task. Bring it out!")
    public void bringItOut(){
        PastebinResultPage resultPage = new PastebinMainPage(getInstance().getDriver()).openPage()
                .enterDataToTextArea(INPUT_TEXT)
                .setSyntaxHighlightToBash(EXPECTED_SYNTAX_HIGHLIGHT)
                .setPasteExpirationParameterToTenMinutes(EXPIRATION_TIME)
                .setTitleForPaste(TITLE_FOR_PASTE)
                .clickCreateNewPasteButton();

        assertThat(resultPage.getPageTitle()).as("Page titles are NOT matched").isEqualTo(EXPECTED_RESULT_PAGE_TITLE);

        SoftAssertions softAssertions = new SoftAssertions();
        resultPage.getBashElements().forEach(x ->
                softAssertions.assertThat(Color.fromString(x.getCssValue("color")).asHex())
                        .as("Syntax color is NOT matched")
                        .isEqualTo(PINK_COLOR));
        softAssertions.assertAll();

        assertThat(resultPage.getTextFromTextArea()).isEqualTo(INPUT_TEXT);
    }

}
