import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumAlertsTest {
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

    @Test
    void test_alert(){
        driver.get(BASE_URL+"/javascript_alerts");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "I am a JS Alert");
    }

    @Test
    void test_confirm(){
        driver.get(BASE_URL+"/javascript_alerts");
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();

        assertEquals(text, "I am a JS Confirm");
    }

    @Test
    void test_prompt(){
        driver.get(BASE_URL+"/javascript_alerts");
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        

        assertEquals(text, "I am a JS prompt");
        alert.sendKeys("my result");
        alert.accept();

        WebElement result = driver.findElement(By.xpath("//p[@id='result']"));
        assertEquals(result.getText(), "You entered: my result");
    }
}
