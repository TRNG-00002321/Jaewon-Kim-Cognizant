import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumInteractionTest {
    private WebDriver driver;
    private final String BASE_URL = "https://the-internet.herokuapp.com";
    @BeforeEach
    public void setup(){
        WebDriverManager.chromiumdriver().setup();

        //init webdriver
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterEach
    public void teardown() {
        //        driver.quit();

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void clickInteractionTest() throws InterruptedException{
        driver.get(BASE_URL + "/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        if(usernameInput.isDisplayed() && usernameInput.isEnabled()){
            usernameInput.clear();
            usernameInput.sendKeys("tomsmith");
            assertEquals(usernameInput.getAttribute("value"), "tomsmith");
        }

        WebElement passwordInput = driver.findElement(By.id("password"));
        if(passwordInput.isDisplayed() && passwordInput.isEnabled()){
            passwordInput.clear();
            passwordInput.sendKeys("SuperSecretPassword!");
            assertEquals(passwordInput.getAttribute("value"), "SuperSecretPassword!");
        }

        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();

        WebElement loginPageText = driver.findElement(By.xpath("//h4[@class='subheader']"));
        assertTrue(loginPageText.getText().contains("Welcome to the Secure Area. When you are done click logout below.") &&
                driver.getCurrentUrl().contains("secure"));
    }
}
