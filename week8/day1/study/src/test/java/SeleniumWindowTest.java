import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWindowTest {
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
    void simple_windows_test() {
        driver.get(BASE_URL+"/windows");
        
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[normalize-space()='Click Here']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

         Set<String> windowHandles = driver.getWindowHandles();

        // Switch to new window
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        assertTrue(driver.getCurrentUrl().contains("new"));
        driver.close();
        driver.switchTo().window(originalWindow);
        assertTrue(driver.getCurrentUrl().contains("windows"));
    }
}
