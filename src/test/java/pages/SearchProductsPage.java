package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SearchProductsPage extends BasePage {

    public SearchProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".single-sub-product")
    List<WebElement> products;

    public boolean verifyProductsAreDisplayed() {
        return products.get(0).isDisplayed();
    }
}
