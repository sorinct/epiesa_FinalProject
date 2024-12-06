package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.BasePage;
import utils.BrowserUtils;
import utils.ConfigUtils;
import utils.ConstantUtils;

public class BaseTest {
    protected WebDriver driver;
    protected String baseURL;
    protected BasePage basePage;

    public void getBrowser() {
        String browserName = ConfigUtils.readGenericElementFromConfig(ConstantUtils.DEFAULT_CONFIG_FILE,
                "browser", "chrome");
        System.out.println("Load browser type: " + browserName);
        driver = BrowserUtils.getDriver(browserName);
        basePage = new BasePage(driver);
    }

    public void setUp() {
        getBaseURL();
        getBrowser();
    }

    private void closeBrowserAtEnd() {
        if (driver != null) {
            System.out.println("Close browser at the end of test");
            driver.quit();
        }
    }

    @AfterMethod
    public void cleanUpAfterMethod() {
        closeBrowserAtEnd();
    }

    public void getBaseURL() {
        getBaseURL(ConstantUtils.DEFAULT_CONFIG_FILE);
    }

    public void getBaseURL(String configFileName) {
        baseURL = ConfigUtils.readGenericElementFromConfig(configFileName, "base.url");
    }

    public void navigateToURL(String path) {
        System.out.println("Open next url: " + baseURL + path);
        driver.navigate().to(baseURL + path);
    }
}
