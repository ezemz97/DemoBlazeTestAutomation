package demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private WebDriverWait wait;

    @FindBy(css = "#tbodyid > div")
    private List<WebElement> products;

    @FindBy(id = "next2")
    private WebElement nextButton;

    @FindBy(id = "cartur")
    private WebElement cart;

    @FindBy(id = "login2")
    private WebElement login;

    @FindBy(id = "signin2")
    private WebElement signUp;

    @FindBy(xpath = "//a[text()='Contact']")
    private WebElement contact;

    private final String PRODUCT_LINK_LOCATOR_TEMPLATE_XPATH = "//a[text()='%s']";
    private final By PRODUCT_LINK_LOCATOR = By.cssSelector(".card-title a");
    private final By PRODUCT_PRICE_LOCATOR = By.cssSelector(".card-block > h5");
    private final By NAME_OF_USER_LOCATOR = By.id("nameofuser");

    public HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
    }

    public List<String> getItemDetails() {
        return products.stream().map(product -> {
            String itemName = product.findElement(PRODUCT_LINK_LOCATOR).getText();
            String itemPrice = product.findElement(PRODUCT_PRICE_LOCATOR).getText();
            String itemLink = product.findElement(PRODUCT_LINK_LOCATOR).getAttribute("href");
            return String.format("Name: %s\nPrice: %s\nLink: %s\n", itemName, itemPrice, itemLink);
        }).collect(Collectors.toList());
    }

    public HomePage clickNextButton() {
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
        wait.until(ExpectedConditions.invisibilityOf(nextButton));
        return new HomePage(driver);
    }

    public ProductPage clickProduct(String productName) {
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_LINK_LOCATOR_TEMPLATE_XPATH, productName)));
        product.click();
        return new ProductPage(driver);
    }

    public CartPage clickCart() {
        wait.until(ExpectedConditions.visibilityOf(cart));
        cart.click();
        return new CartPage(driver);
    }

    public SignUpModalPage clickSignUp() {
        wait.until(ExpectedConditions.visibilityOf(signUp));
        signUp.click();
        return new SignUpModalPage(driver);
    }

    public LoginModalPage clickLogIn() {
        wait.until(ExpectedConditions.visibilityOf(login));
        login.click();
        return new LoginModalPage(driver);
    }

    public String getLoggedInUsername() {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_OF_USER_LOCATOR));
        return username.getText();
    }

    public ContactModalPage clickContact() {
        wait.until(ExpectedConditions.visibilityOf(contact));
        contact.click();
        return new ContactModalPage(driver);
    }
}