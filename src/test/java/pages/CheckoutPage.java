package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


//todo .....

public class CheckoutPage extends BasePage {

    public static final String PATH = "checkout-epiesa/";


    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cumparaturi-text>p")
    WebElement productText;

    @FindBy(css = ".plus")
    WebElement increaseQuantityPlusSign;

    @FindBy(css = ".minus")
    WebElement decreaseQuantityMinusSign;

    @FindBy(css = ".delete-bttn")
    WebElement itemDeleteButton;

    @FindBy(css = "#aaa_st")
    WebElement subTotal;

    @FindBy(css = "#aaa2")
    WebElement total;

    @FindBy(css = "[action=\"/cos-cumparaturi-epiesa/#aicisha\"]>h4")
    WebElement emptyCartText;

    public void clickOnIncreaseQuantityPlusSign() {
        increaseQuantityPlusSign.click();
    }

    public void clickOnDecreaseQuantityMinusSign() {
        decreaseQuantityMinusSign.click();
    }

    public void clickOnItemDeleteButton() {
        itemDeleteButton.click();
    }

    public String verifyProductTextIsCorrect() {
        return productText.getText();
    }

}
