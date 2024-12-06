package tests.e2e;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactPage;
import pages.HomePage;
import tests.BaseTest;
import java.io.File;
import java.io.IOException;
import static utils.enums.CookiesActions.ACCEPT;

public class ContactTests extends BaseTest {

    // Test data in json reading
    JsonNode jsonReader;

    // Page objects used through the tests
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    // Precondition - Opening the Homepage and Accept the Cookies
    @BeforeMethod
    public void openHomepageAndAcceptCookies() throws IOException {
        File file = new File("src/test/resources/testdata/contactData.json");

        ObjectMapper mapper = new ObjectMapper();
        jsonReader = mapper.readTree(file);

        setUp();
        navigateToURL(HomePage.PATH);
        homePage = new HomePage(driver);

        // Accept cookies
        homePage.handleCookieDialog(ACCEPT);
    }

    /**
     * Test Case 9: Ensure clicking on the "Contact" link redirects to the contact page and that contact details are displayed correctly
     * Steps:
     * Open the homepage
     * Locate and click the "Contact" link
     * Expected Result: Verify that the browser navigates to the contact page
     * Check that the contact page displays the following details: Phone number, Email address, title.
     */

    @Test
    public void verifyContactDetailsAppearsCorrectly() {

        homePage.clickOnContactButton();
        ContactPage contactPage = new ContactPage(driver);

        // Verify every text to be correct
        softAssert.assertEquals(jsonReader.get("title").asText(), contactPage.verifyContactTitle(), "Contact title is not correct");
        softAssert.assertEquals(jsonReader.get("phone_number").asText(), contactPage.verifyPrimaryPhone(),
                "Contact Phone number is not correct");
        softAssert.assertEquals(jsonReader.get("email").asText(), contactPage.verifyEmailValue(), "Contact Email is not correct");

        // Collect and report all failures
        softAssert.assertAll();
    }
}
