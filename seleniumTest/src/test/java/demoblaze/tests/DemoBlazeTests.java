package demoblaze.tests;

import demoblaze.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DemoBlazeTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        String username = "user_" + UUID.randomUUID();
        String password = "pass_" + UUID.randomUUID();
        return new Object[][] {
                { username, password }
        };
    }

    @Test
    public void exportProductDetailsTest() {
        HomePage homePage = new HomePage(driver);

        List<String> itemDetails = homePage.getItemDetails();
        homePage = homePage.clickNextButton();
        itemDetails.addAll(homePage.getItemDetails());

        String directoryPath = "src/test/java/demoblaze/resources/output";
        String filePath = directoryPath + "/productDetails.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String detail : itemDetails) {
                writer.write(detail);
                writer.write("\n");
            }
        } catch (IOException e) {
            Assert.fail("Failed to create product details file, check file path.");
            e.printStackTrace();
        }

        File file = new File(filePath);
        Assert.assertTrue(file.exists(), "The file was successfully created.");
    }

    @Test
    public void purchaseProductTest() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.clickProduct("Nexus 6");
        productPage.addToCart();
        homePage = productPage.navigateToHomepage();
        CartPage cartPage = homePage.clickCart();
        PlaceOrderModalPage placeOrderModalPage = cartPage.placeOrder();
        SuccessPurchaseModalPage successPurchaseModalPage = placeOrderModalPage
                .setName("John")
                .setCountry("Uruguay")
                .setCity("Montevideo")
                .setCreditCard("5547 4899 2164 2265")
                .setMonth("07")
                .setYear("2024")
                .clickPurchase();
        Assert.assertTrue(successPurchaseModalPage.isSuccessIconVisible(), "Success icon is visible");
    }

    @Test(dataProvider = "userDataProvider")
    public void signUpAndLoginTest(String username, String password) {
        HomePage homePage = new HomePage(driver);
        SignUpModalPage signUpModalPage = homePage.clickSignUp();
        signUpModalPage
                .setUsername(username)
                .setPassword(password)
                .clickSignUp();
        if (signUpModalPage.isAlertPresent()) {
            String alertText = signUpModalPage.getAlertText();
            Assert.assertEquals(alertText, "Sign up successful.");
        } else {
            Assert.fail("Sign up alert is not displayed");
        }
        homePage = signUpModalPage.acceptAlert();
        LoginModalPage logInModalPage = homePage.clickLogIn();
        homePage = logInModalPage
                .setUsername(username)
                .setPassword(password)
                .clickLogIn();
        String userWelcome = homePage.getLoggedInUsername();
        Assert.assertEquals(userWelcome, "Welcome " + username);
    }

    @Test
    public void sendContactMessageTest() {
        HomePage homePage = new HomePage(driver);
        ContactModalPage contactModalPage = homePage.clickContact();
        contactModalPage
                .setContactEmail("test@test.com")
                .setContactName("Ezequiel")
                .setMessage("This is an automated test")
                .clickSendMessage();
        if (contactModalPage.isAlertPresent()) {
            String alertText = contactModalPage.getAlertText();
            Assert.assertEquals(alertText, "Thanks for the message!!");
        } else {
            Assert.fail("Message sent alert is not visible");
        }

    }
}