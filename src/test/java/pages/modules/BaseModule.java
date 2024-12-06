package pages.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;



// POJO = Plain Old Java Object

public abstract class BaseModule {
    protected WebDriver driver;

    public BaseModule(WebDriver driver) {
        this.driver = driver;

//      shortcut @Findby
        PageFactory.initElements(driver, this);
    }
}
