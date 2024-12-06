package tests.e2e;

import models.AccountModel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthenticatePage;
import tests.BaseTest;
import static utils.enums.CookiesActions.ACCEPT;


public class InvalidUserRegisterTests extends BaseTest {

    // page objects used through the tests
    AuthenticatePage authenticatePage;

    // precondition - Opening the Register page and Accept the Cookies
    @BeforeMethod
    public void openAuthenticationPageAndAcceptCookies() {
        setUp();
        navigateToURL(AuthenticatePage.PATH);
        authenticatePage = new AuthenticatePage(driver);

        // accept cookies
        authenticatePage.handleCookieDialog(ACCEPT);
    }

    /**
     * Test Case 2: Invalid User Registration
     * Objective: Verify that a new user can use the same adress email to create an account
     * Steps:
     * Navigate to the login page and select the "CONT NOU" option.
     * Fill in required registration fields (name,an existent adress email already used, password, etc.).
     * Submit the registration form.
     * Expected Result: User
     */


    @Test(dataProvider = "loginSQLDataProvider", dataProviderClass = dataproviders.LoginDP.class)
    public void verifyThatANewUserCanSuccessfullyRegisterWithAnUsedEmail(AccountModel accountModel) {
        // enter correct credentials
        authenticatePage.enterNewAccountEmail(accountModel.getEmail());
        authenticatePage.enterNewAccountLastName(accountModel.getLastName());
        authenticatePage.enterNewAccountFirstName(accountModel.getFirstName());
        authenticatePage.enterNewAccountPassword(accountModel.getPassword());
        authenticatePage.enterNewAccountConfirmPassword(accountModel.getPassword());
        authenticatePage.clickOnRegisterButton();

        String expectedMessageEmail = "Aceasta adresa de email exista deja in baza de date !";
        String actualMessageEmail = authenticatePage.getErrorMessage();
        Assert.assertEquals(actualMessageEmail, expectedMessageEmail,
                "Eroare inregistrare user - adresa email folosita!");
    }
}