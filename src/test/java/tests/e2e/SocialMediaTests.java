package tests.e2e;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import tests.BaseTest;
import java.util.Iterator;
import java.util.Set;
import static utils.enums.CookiesActions.ACCEPT;

public class SocialMediaTests extends BaseTest {

//     test data
    public static final String YOUTUBE_EPIESA_URL = "https://www.youtube.com/@epiesa2589";

//     page objects used through the tests
    HomePage homePage;

//     precondition - Opening the Homepage and Accept the Cookies

    @BeforeMethod
    public void openHomepageAndAcceptCookies() {
        setUp();
        navigateToURL(HomePage.PATH);
        homePage = new HomePage(driver); // incep sa lucrez pe pagina de Home

        // accept cookies
        homePage.handleCookieDialog(ACCEPT);
    }

    /**
     * Test Case 4: Verify YouTube Link on Homepage
     * Objective: Verify that clicking the YouTube social media link redirects the user to the correct YouTube page
     * Steps:
     * Open the Homepage
     * Locate the YouTube social media icon in the footer section
     * Click on the YouTube icon
     * Expected Result: Verify that a new tab or window opens with the YouTube channel/page
     * Check the URL of the opened page to confirm it matches the expected YouTube channel URL for epiesa.ro
     */
    @Test
    public void verifyThatYouTubeOpensCorrectly() {
        // locate and click on YouTube icon
        homePage.clickOnYouTubeIcon();

        // Get handles of all open tabs
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        // Switch to the new tab
        String originalTab = iterator.next();
        String newTab = iterator.next();
        driver.switchTo().window(newTab);

        // click on reject all cookies on YouTube (I didn't  create a page object)
        driver.findElement(By.cssSelector(".KZ9vpc [aria-label=\"Reject all\"]")).click();

        // verify the first part of the URl before the query params is correct
        Assert.assertEquals(driver.getCurrentUrl().split("\\?")[0], YOUTUBE_EPIESA_URL,
                "The URL is not correct");
    }
}
