package demoblaze.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(css = ".btn-success")
    private WebElement addToCart;

    @FindBy(id = "nava")
    private WebElement homePage;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCart));
        addToCart.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public HomePage navigateToHomepage() {
        homePage.click();
        return new HomePage(driver);
    }



}
