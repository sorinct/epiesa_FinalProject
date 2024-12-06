package pages.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartModule extends BaseModule {

    public CartModule(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".fixed-scroll.desk .shopping-basket+p")
    WebElement cartProductsNumberOfItems;

    @FindBy(css = ".fixed-scroll.desk [title=\"cos cumparaturi epiesa.ro\"]")
    WebElement myCart;

    public void clickOnCart() {
        myCart.click();
    }

    public int getCartProductsNumberOfItems() {
        return Integer.parseInt(cartProductsNumberOfItems.getText());
    }

}
