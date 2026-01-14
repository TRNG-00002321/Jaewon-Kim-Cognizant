package e2e;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        //Step one: Setup WebDriver
        WebDriverManager.chromedriver().setup();

        //Step two: Define browser options
        ChromeOptions options = new ChromeOptions();

        //Step three: Initialize WebDriver
        this.driver = new ChromeDriver(options);

        //Step four(optional): Setup implicit wait
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void testOpenHomePage() {
        driver.get("http://example.com");
        assertEquals("Example Domain", driver.getTitle());
    }

    @Test
    public void testMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com/");
        String originalWindow = driver.getWindowHandle();

        Actions actions = new Actions(driver);

        WebElement authLinkCss = driver.findElement(By.cssSelector("a[href='/login']"));
        WebElement authLinkRelXpath = driver.findElement(By.xpath("//a[text()='Form Authentication']"));
        actions.keyDown(Keys.CONTROL).click(authLinkRelXpath).keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).click(authLinkCss).keyUp(Keys.CONTROL).perform();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(numberOfWindowsToBe(3));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow) && !windowHandle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windowHandle);
            }
        }
        assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(numberOfWindowsToBe(1));
        driver.switchTo().window(originalWindow);
        assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/");
    }
}
