import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumSelectTest {
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
    public void selectDropdown(){
        driver.get(BASE_URL + "/dropdown");
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        WebElement we;

        dropdownSelect.selectByVisibleText("Option 1");
        we = dropdownSelect.getFirstSelectedOption();
        assertEquals("Option 1", we.getText());

        dropdownSelect.selectByValue("2");
        we = dropdownSelect.getFirstSelectedOption();
        assertEquals("Option 2", we.getText());
    }
}
