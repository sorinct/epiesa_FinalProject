package tests.e2e;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.AuthenticatePage;
import pages.HomePage;
import tests.BaseTest;
import static utils.enums.CookiesActions.ACCEPT;

public class ValidUserLoginTests extends BaseTest {

    public static final String USERNAME = "blocnarrecords1@gmail.com";
    public static final String PASSWORD = "Test123.";

    // page objects used through the tests
    HomePage homePage;
    AccountPage accountPage;


    @BeforeMethod
    public void openHomepageAndAcceptCookies() {
        setUp();
        navigateToURL(HomePage.PATH);
        homePage = new HomePage(driver); // incep sa lucrez pe pagina de Home

        // accept cookies
        homePage.handleCookieDialog(ACCEPT);
    }

    /**
     * Test Case 1: User Login
     * Objective: Verify that a registered user can successfully log in.
     * Steps:
     * Navigate to the login page.
     * Enter a valid username and password.
     * Click on the "LOGIN / CONT NOU" button.
     * Expected Result: User is redirected to their account dashboard and sees a welcome message.
     */

    @Test(dataProvider = "loginDataProvider", dataProviderClass = dataproviders.LoginDP.class)
    public void verifyThatARegisteredUserCanSuccessfullyLogIn(String username, String password) {
        homePage.hoverOnMyAccount();
        // click login
        homePage.clickOnLogin(); // sunt redirectionat pe pagina de Login

        AuthenticatePage loginPage = new AuthenticatePage(driver);
        // enter address email and password
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        // click authenticate
        loginPage.clickOnAuthenticateButton(); // sunt redirectionat pe pagina de Account

        accountPage = new AccountPage(driver); // incep sa lucrez pe pagina de Account
        Assert.assertEquals(accountPage.verifyMyAccountEmailIsCorrect(),
                "Email: " + username,
                "The email is not correct");
    }

    /**
     * Test Case 10: Logout
     * Objective: Verify that a logged-in user can successfully log out.
     * Steps:
     * After logging in, click on the "Contul meu" dropdown.
     * Select the "Logout" option.
     * Expected Result: User is logged out and redirected to the homepage, or the login page, with the login option
     */

    @Test
    public void verifyThatALoggedInUserCanSuccessfullyLogOut() {
        verifyThatARegisteredUserCanSuccessfullyLogIn(USERNAME, PASSWORD);
        homePage.hoverOnMyAccount();
        accountPage.clickOnSignOut();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseURL + HomePage.PATH,
                "Sign out did not redirect to Homepage");
        Assert.assertTrue(homePage.verifyLoginAndMyAccountHeaderIsDisplayed(), "The email is not correct");
    }
}