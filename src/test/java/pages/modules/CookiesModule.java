package pages.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookiesModule extends BaseModule {

    public CookiesModule(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#termsfeed-com---nb")
    WebElement dialog;

    @FindBy(css = ".cc-nb-okagree")
    WebElement acceptButton;

    @FindBy(css = ".cc-nb-reject")
    WebElement rejectButton;

    public void clickToAccept(){
        acceptButton.click();
    }

    public void clickToReject(){
        rejectButton.click();
    }

    public boolean isDialogVisible() {
        return dialog.isDisplayed();
    }
}
