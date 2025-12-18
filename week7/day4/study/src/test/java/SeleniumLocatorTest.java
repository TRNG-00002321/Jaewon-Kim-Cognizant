import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@DisplayName("Element Selenium Test")
public class SeleniumLocatorTest {

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

    @DisplayName("Test by Id")
    @Test
    public void testID(){
        driver.get(BASE_URL + "/login");
        WebElement userName = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        System.out.println(userName);
        System.out.println(password);

        assertTrue(userName.isDisplayed());
        assertTrue(password.isDisplayed());
    }

    @DisplayName("Test by Name")
    @Test
    public void testByName(){
        driver.get(BASE_URL + "/login");
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        System.out.println(userName);
        System.out.println(password);

        assertTrue(userName.isDisplayed());
        assertTrue(password.isDisplayed());
    }

    @DisplayName("Test by tag")
    @Test
    public void testByTag(){
        driver.get(BASE_URL + "/login");
        List<WebElement> tagList = driver.findElements(By.tagName("input"));
        System.out.println(tagList);

        for(WebElement e: tagList){
            System.out.println(e);
            assertTrue(e.isDisplayed());
        }
    }

    @DisplayName("Test Login Button")
    @Test
    public void testLoginButton(){
        driver.get(BASE_URL + "/login");
        //this is the login button
        List<WebElement> tagList = driver.findElements(By.className("radius"));
        System.out.println(tagList);

        for(WebElement e: tagList){
            String title = e.getText();
            System.out.println(title);
            assertTrue(title.contains("Login"));
        }
    }

    @DisplayName("Test by Absolute XPath")
    @Test
    public void testAbsoluteXPath(){
        driver.get(BASE_URL);
        //this is the login button
        List<WebElement> tagList = driver.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]"));
        System.out.println(tagList);
        assertTrue(!tagList.isEmpty());

        for(WebElement e: tagList){
            String tagText = e.getText();
            System.out.println(tagText);
            assertTrue(tagText.contains("Available Examples"));
        }
    }

        @DisplayName("Test by Absolute XPath for Login")
    @Test
    public void testAbsoluteXPathLogin(){
        driver.get(BASE_URL+"/login");
        //this is the login button
        List<WebElement> tagList = driver.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/label[1]"));
        System.out.println(tagList);
        assertTrue(!tagList.isEmpty());

        for(WebElement e: tagList){
            String tagText = e.getText();
            System.out.println(tagText);
            assertTrue(tagText.contains("Username"));
        }
    }

    @DisplayName("Test by Relative XPath")
    @Test
    //h2[normalize-space()='Available Examples']
    public void testRealtiveXPath(){
        driver.get(BASE_URL);
        //this is the login button
        List<WebElement> tagList = driver.findElements(By.xpath("//h2[normalize-space()='Available Examples']"));
        System.out.println(tagList);
        assertTrue(!tagList.isEmpty());

        for(WebElement e: tagList){
            String tagText = e.getText();
            System.out.println(tagText);
            assertTrue(tagText.contains("Available Examples"));
        }
    }
}