package tests.e2e;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import tests.BaseTest;
import static utils.enums.CookiesActions.ACCEPT;

public class CartTests extends BaseTest {

    // error message epiesa
    public static final String EMPTY_CART_TEXT_MESSAGE = "Cosul de cumparaturi este gol.";

//     page objects used through the tests
    ProductPage productPage;
    CartPage cartPage;

//    precondition -  Opening the product page and Accept the Cookies

    @BeforeMethod
    public void openProductPageAndAcceptCookies() {
        setUp();
        navigateToURL(ProductPage.PATH); // pagina produsului
        productPage = new ProductPage(driver); // pagina de ProductPage

//      accept cookies
        productPage.handleCookieDialog(ACCEPT);
    }

    /**
     * Test Case 5: Add Product to Cart
     * Objective: Verify that a user can add a search product to their cart.
     * Steps:
     * Search the product by name
     * Click on "Add to Cart" button.
     * Expected Result: Product is added to the cart, and cart icon updates to show the item count.
     */

    @Test(dataProvider = "cartDataProvider", dataProviderClass = dataproviders.CartDP.class)
    public void addProductToCart(String productName, int productPrice) {

        productPage.clickOnAddToCart();
        Assert.assertEquals(1, productPage.getCartProductsNumberOfItems(),
                "The Item was not added to cart correctly");
    }

    /**
     * Test Case 6: View Cart
     * Objective: Verify that a user can view their shopping cart.
     * Steps:
     * Click on the "Cosul meu" button/icon to go to the cart page.
     * Expected Result: Cart page displays the added product name is correct.
     */

    @Test(dataProvider = "cartDataProvider", dataProviderClass = dataproviders.CartDP.class)
    public void viewCart(String productName, int productPrice) {
        // precondition add a product to cart
        addProductToCart(productName, productPrice);

        productPage.clickOnCart();
        cartPage = new CartPage(driver);
        Assert.assertEquals(productName,
                cartPage.verifyProductTextIsCorrect(),
                "None or not the correct item was added to cart");
    }

    /**
     * Test Case 7: Update Product Quantity in Cart
     * Objective: Verify that a user can change the quantity of a product in the cart.
     * Steps:
     * Open the cart.
     * Adjust the quantity of a product.
     * Expected Result: Product quantity updates, and the total price reflects the change.
     */


    @Test(dataProvider = "cartDataProvider", dataProviderClass = dataproviders.CartDP.class)
    public void updateProductQuantityInCart(String productName, int productPrice) {
        // precondition add a product to cart and go to the cart page
        viewCart(productName, productPrice);
        // set a variable to hold the price of one piece of that item
        int priceForOneItem = cartPage.verifySubTotalPrice();
        // optional, verify the price is the same as in data-provider
        Assert.assertEquals(productPrice, priceForOneItem, "The price is incorrect or has changed");

        // click on plus sign to increase quantity
        cartPage.clickOnIncreaseQuantityPlusSign();
        Assert.assertEquals(priceForOneItem * 2,
                cartPage.verifySubTotalPrice(),
                "The price for two pieces is not correct");
    }

    /**
     * Test Case 8: Remove Product from Cart
     * Objective: Verify can remove a product from cart.
     * Steps:
     * Open the cart.
     * Click the "Remove" button next to a product.
     * Expected Result: Product is removed from the cart, and the total updates accordingly.
     */

    @Test(dataProvider = "cartDataProvider", dataProviderClass = dataproviders.CartDP.class)
    public void removeProductFromCart(String productName, int productPrice) {
        // precondition add a product to cart and go to the cart page
        viewCart(productName, productPrice);

        // click on the delete button
        cartPage.clickOnItemDeleteButton();
        Assert.assertEquals(EMPTY_CART_TEXT_MESSAGE,
                cartPage.verifyEmptyCartText(),
                "The cart message is not correct");
    }
}
