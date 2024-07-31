package demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessPurchaseModalPage extends BasePage {
    private WebDriverWait wait;

    @FindBy(css = ".sa-success")
    private WebElement successIcon;

    @FindBy(css = "button.confirm")
    private WebElement confirmButton;

    public SuccessPurchaseModalPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(successIcon));
    }

    public HomePage clickOkButton() {
        wait.until(ExpectedConditions.visibilityOf(confirmButton));
        confirmButton.click();
        return new HomePage(driver);
    }

    public boolean isSuccessIconVisible() {
        return successIcon.isDisplayed();
    }
}
