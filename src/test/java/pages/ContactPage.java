package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends BasePage {

    public static final String PATH = "contact-epiesa/";

    public ContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".contact-left h1")
    WebElement contactTitle;

    @FindBy(css = ".suport-clienti>a:first-of-type")
    WebElement primaryPhone;

    @FindBy(css = ".email a")
    WebElement emailValue;

    public String verifyContactTitle() {
        return contactTitle.getText();
    }

    public String verifyPrimaryPhone() {
        return primaryPhone.getText();
    }

    public String verifyEmailValue() {
        return emailValue.getText();
    }

}
