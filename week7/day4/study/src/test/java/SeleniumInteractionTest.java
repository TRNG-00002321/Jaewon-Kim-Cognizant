import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumInteractionTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://the-internet.herokuapp.com";
    
    @BeforeEach
    public void setup(){
        WebDriverManager.chromiumdriver().setup();
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Initialize WebDriverWait with 10 second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void clickInteractionTest() {
        driver.get(BASE_URL + "/login");

        // Wait for username input to be visible and enabled
        WebElement usernameInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        
        usernameInput.clear();
        usernameInput.sendKeys("tomsmith");
        assertEquals("tomsmith", usernameInput.getAttribute("value"));

        // Wait for password input to be visible and enabled
        WebElement passwordInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        
        passwordInput.clear();
        passwordInput.sendKeys("SuperSecretPassword!");
        assertEquals("SuperSecretPassword!", passwordInput.getAttribute("value"));

        // Wait for login button to be clickable
        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-2x fa-sign-in']"))
        );
        loginButton.click();

        // Wait for successful login - page text to be visible
        WebElement loginPageText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='subheader']"))
        );
        
        // Wait for URL to contain "secure"
        wait.until(ExpectedConditions.urlContains("secure"));
        
        assertTrue(loginPageText.getText().contains("Welcome to the Secure Area. When you are done click logout below."));
        assertTrue(driver.getCurrentUrl().contains("secure"));
    }
}
