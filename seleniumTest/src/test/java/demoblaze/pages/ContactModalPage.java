package demoblaze.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactModalPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//button[text()='Send message']")
    private WebElement sendMessageButton;

    @FindBy(id = "recipient-email")
    private WebElement contactEmail;

    @FindBy(id = "recipient-name")
    private WebElement contactName;

    @FindBy(id = "message-text")
    private WebElement messageBox;

    public ContactModalPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(sendMessageButton));
    }

    public ContactModalPage setContactEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(contactEmail));
        contactEmail.sendKeys(email);
        return this;
    }

    public ContactModalPage setContactName(String name) {
        wait.until(ExpectedConditions.visibilityOf(contactName));
        contactName.sendKeys(name);
        return this;
    }

    public ContactModalPage setMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(messageBox));
        messageBox.sendKeys(message);
        return this;
    }

    public void clickSendMessage() {
        wait.until(ExpectedConditions.visibilityOf(sendMessageButton));
        sendMessageButton.click();
    }

    public boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

}
