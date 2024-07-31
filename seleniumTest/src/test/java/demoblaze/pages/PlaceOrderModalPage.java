package demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlaceOrderModalPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;


    public PlaceOrderModalPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(purchaseButton));
    }

    public PlaceOrderModalPage setName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        return this;
    }

    public PlaceOrderModalPage setCountry(String country) {
        wait.until(ExpectedConditions.visibilityOf(countryField));
        countryField.sendKeys(country);
        return this;
    }

    public PlaceOrderModalPage setCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(cityField));
        cityField.sendKeys(city);
        return this;
    }

    public PlaceOrderModalPage setCreditCard(String creditCard) {
        wait.until(ExpectedConditions.visibilityOf(cardField));
        cardField.sendKeys(creditCard);
        return this;
    }

    public PlaceOrderModalPage setMonth(String month) {
        wait.until(ExpectedConditions.visibilityOf(monthField));
        monthField.sendKeys(month);
        return this;
    }

    public PlaceOrderModalPage setYear(String year) {
        wait.until(ExpectedConditions.visibilityOf(yearField));
        yearField.sendKeys(year);
        return this;
    }

    public SuccessPurchaseModalPage clickPurchase() {
        wait.until(ExpectedConditions.visibilityOf(purchaseButton));
        purchaseButton.click();
        return new SuccessPurchaseModalPage(driver);
    }


}
