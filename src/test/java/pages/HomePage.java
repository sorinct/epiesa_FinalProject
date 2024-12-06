package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public static final String PATH = "";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".contul-meu")
    WebElement myAccountDropDown;

    @FindBy(css = ".contul-meu+.dropdown-menu [href=\"/autentificare-epiesa/\"]")
    WebElement loginAndMyAccountHeader;

    @FindBy(css = "[class=\"contul-meu-right\"] a")
    WebElement myAccountEmail;

    @FindBy(css = "[alt=\"canal youtube epiesa.ro\"]")
    WebElement youTubeIcon;

    @FindBy(css = ".contact [title=\"contact epiesa.ro\"]")
    WebElement contactButton;

    public void hoverOnMyAccount() {
        new Actions(driver).moveToElement(myAccountDropDown).perform();
    }

    public void clickOnLogin() {
        loginAndMyAccountHeader.click();
    }

    public void clickOnYouTubeIcon() {
        new Actions(driver).moveToElement(youTubeIcon).click().perform();
    }

    public void clickOnContactButton() {
        contactButton.click();
    }

    public boolean verifyLoginAndMyAccountHeaderIsDisplayed() {
        hoverOnMyAccount();
        waitUntilElementVisible(loginAndMyAccountHeader);
        return loginAndMyAccountHeader.isDisplayed();
    }
}
