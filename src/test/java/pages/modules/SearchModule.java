package pages.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchModule extends BaseModule {

    public SearchModule(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header-form>form[action=\"/cautare-piesa/\"]>[name=\"find\"]")
    public WebElement searchBar;

    @FindBy(css = ".header-form>form[action=\"/cautare-piesa/\"]>button")
    public WebElement searchIconButton;

    public void enterInSearchBar(String text) {
        searchBar.sendKeys(text);
    }

    public void clickSearchIconButton() {
        searchIconButton.click();
    }
}
