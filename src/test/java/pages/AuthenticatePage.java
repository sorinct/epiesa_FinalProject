package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticatePage extends BasePage {
    public static final String PATH = "autentificare-epiesa/";

    public AuthenticatePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name=\"login_utilizator\"]")
    WebElement usernameInput;

    @FindBy(css = "[name=\"login_parola\"]")
    WebElement passwordInput;

    @FindBy(css = "[name=\"form_autentificare\"] [type=\"submit\"]")
    WebElement authenticateButton;

    @FindBy(css = "[name=\"email\"]")
    WebElement newAccountEmail;

    @FindBy(css = "[name=\"nume\"]")
    WebElement newAccountLastName;

    @FindBy(css = "[name=\"prenume\"]")
    WebElement newAccountFirstName;

    @FindBy(css = "[name=\"parola\"]")
    WebElement newAccountPassword;

    @FindBy(css = "[name=\"confirmare_parola\"]")
    WebElement newAccountConfirmPassword;

    @FindBy(xpath = "//*[@name=\"action_autentificare\"]/preceding-sibling::button")
    WebElement registerButton;

    @FindBy(css = "div.contact-row > p")
    WebElement errorMessageElement;

    public void enterUserName(String emailAddress){
        usernameInput.sendKeys(emailAddress);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickOnAuthenticateButton(){
        authenticateButton.click();
    }

    public void enterNewAccountEmail(String email) {
        newAccountEmail.sendKeys(email);
    }

    public void enterNewAccountLastName(String lastname) {
        newAccountLastName.sendKeys(lastname);
    }

    public void enterNewAccountFirstName(String fisrtname) {
        newAccountFirstName.sendKeys(fisrtname);
    }

    public void enterNewAccountPassword(String password) {
        newAccountPassword.sendKeys(password);
    }

    public void enterNewAccountConfirmPassword(String password) {
        newAccountConfirmPassword.sendKeys(password);
    }

    public void clickOnRegisterButton() {
        new Actions(driver).moveToElement(registerButton).click().perform();
    }
    public String getErrorMessage() {
        return errorMessageElement.getText();
    }
}

