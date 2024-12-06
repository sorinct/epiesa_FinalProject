package tests.e2e;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchProductsPage;
import tests.BaseTest;
import static utils.enums.CookiesActions.ACCEPT;


public class ProductsSearchTests extends BaseTest {

    HomePage homePage;

    // precondition - Opening the Homepage and Accept the Cookies

    @BeforeMethod
    public void openHomepageAndAcceptCookies() {
        setUp();
        navigateToURL(HomePage.PATH);
        homePage = new HomePage(driver);
        homePage.handleCookieDialog(ACCEPT);
    }

    /**
     * Test Case 3: Search for a Product
     * Objective: Verify that a user can search for a product.
     * Steps:
     * Enter a product name ("placute frana") into the search bar.
     * Click on the search icon or press Enter.
     * Expected Result: Search results page displays products related to the entered term.
     */
    @Test(dataProvider = "searchDataProvider", dataProviderClass = dataproviders.SearchDP.class)
    public void verifyThatAUserCanSearchForAProduct(String searchText) {
        // enter text in search bar and click on icon
        homePage.enterInSearchBar(searchText);
        homePage.clickSearchIconButton(); // SearchProducts

        SearchProductsPage searchProductsPage = new SearchProductsPage(driver); // SearchProducts
        Assert.assertTrue(searchProductsPage.verifyProductsAreDisplayed(), "Products were not found");
    }
}
