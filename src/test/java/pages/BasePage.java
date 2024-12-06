package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.modules.CartModule;
import pages.modules.CookiesModule;
import pages.modules.SearchModule;
import utils.enums.CookiesActions;
import java.time.Duration;
import static utils.enums.CookiesActions.*;

public class BasePage {
    // Driver
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;

    // Modules
    protected CookiesModule cookiesModule;
    protected SearchModule searchModule;
    protected CartModule cartModule;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initiate modules
        this.cookiesModule = new CookiesModule(driver);
        this.searchModule = new SearchModule(driver);
        this.cartModule = new CartModule(driver);
    }

    // Cookies module
    public void handleCookieDialog(CookiesActions action) {
        if (cookiesModule.isDialogVisible()) {
            if (action.equals(ACCEPT)) {
                cookiesModule.clickToAccept();
            } else if (action.equals(REJECT)) {
                cookiesModule.clickToReject();
            }
        }
    }

    // Search module
    public void enterInSearchBar(String text) {
        searchModule.enterInSearchBar(text);
    }

    public void clickSearchIconButton() {
        searchModule.clickSearchIconButton();
    }

    // Cart module icon
    public int getCartProductsNumberOfItems() {
        return cartModule.getCartProductsNumberOfItems();
    }

    public void clickOnCart() {
        cartModule.clickOnCart();
    }

    public void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}