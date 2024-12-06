package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage {
    public static final String PATH = "admin-cont-epiesa/";

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".contul-meu+.dropdown-menu [href=\"/logout-epiesa/\"]")
    WebElement singOutButton;
    @FindBy(css = "[class=\"contul-meu-right\"] a")
    WebElement myAccountEmail;

    public void clickOnSignOut() {
        singOutButton.click();
    }

    public String verifyMyAccountEmailIsCorrect() {
        waitUntilElementVisible(myAccountEmail);
        return myAccountEmail.getText();
    }
}
