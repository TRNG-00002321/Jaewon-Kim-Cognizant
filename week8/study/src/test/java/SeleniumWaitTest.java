import java.time.Duration;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWaitTest {
    private WebDriver driver;
    private final String BASE_URL = "https://the-internet.herokuapp.com";
    
    @BeforeEach
    public void setup(){
        WebDriverManager.chromiumdriver().setup();
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    //doesnt work for dynamic content
    @Test
    void without_wait_fails(){
        driver.get(BASE_URL+"/dynamic_loading/1");

        driver.findElement(By.xpath("//button[text()='Start']")).click();
        WebElement result = driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']"));
        String text = result.getText(); 

        assertEquals("", text);
    }

    //this test wont wait since the element we are loaing exsist in the dom but is hidden
    @Test
    void test_implicit_wait_fails(){
        //setting implicit wait for all elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(BASE_URL+"/dynamic_loading/1");

        driver.findElement(By.xpath("//button[text()='Start']")).click();
        WebElement result = driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']"));
        String text = result.getText(); 

        assertEquals("", text);
    }

    @Test
    void test_explicit_wait(){
        driver.get(BASE_URL+"/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h4[normalize-space()='Hello World!']")
        ));

        String text = result.getText();
        assertEquals("Hello World!", text);
    }

    @Test
    void test_fluent_waits(){
        driver.get(BASE_URL+"/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class)
            .withMessage("Waiting for the result element");

        WebElement result = fluentWait.until(driver -> {
            WebElement element = driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']"));
            // Check if element is displayed before returning it
            if (element.isDisplayed()) {
                return element;
            }
            return null; // Return null to continue waiting
        });

        String text = result.getText();
        assertEquals("Hello World!", text);
    }
}
