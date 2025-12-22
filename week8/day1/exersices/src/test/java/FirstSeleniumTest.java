
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

class FirstSeleniumTest extends BaseTest {

    @Test
    @DisplayName("Navigate to Google and verify title")
    void testNavigateToGoogle() {
        // Navigate to URL
        driver.get("https://www.google.com");

        // Get page title
        String title = driver.getTitle();

        // Verify title
        assertTrue(title.contains("Google"), "Page title should contain 'Google'");
    }

    @Test
    @DisplayName("Navigate to Example.com and verify content")
    void testNavigateToExample() {
        // Navigate
        driver.get("https://example.com");

        // Get title and URL
        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();

        // Assertions
        assertEquals("Example Domain", title);
        assertTrue(currentUrl.contains("example.com"));

        // Find element and verify text
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Example Domain", heading.getText());
    }

    @Test
    @DisplayName("Navigate to practice site and find elements")
    void testFindElements() {
        // Navigate to a practice site
        driver.get("https://the-internet.herokuapp.com/");

        // Find heading
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Welcome to the-internet", heading.getText());

        // Find link by link text
        WebElement formAuthLink = driver.findElement(By.linkText("Form Authentication"));
        assertTrue(formAuthLink.isDisplayed());

        // Get page source
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Available Examples"));
    }

    @Test
    @DisplayName("Navigate to Google and verify title")
    void testNavigateToYoutube() {
        // Navigate to URL
        driver.get("https://www.youtube.com/");

        // Get page title
        String title = driver.getCurrentUrl();

        // Verify title
        assertTrue(title.contains("youtube"), "Page title should contain 'youtube'");
    }

    @Test
    @DisplayName("Fill and submit login form")
    void testLoginForm() {
        // Navigate to login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Find username field and enter text
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        // Find password field and enter text
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        // Find and click submit button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Verify success message
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Test invalid login")
    void testInvalidLogin() {
        driver.get("https://the-internet.herokuapp.com/login");

        // Enter invalid credentials
        driver.findElement(By.id("username")).sendKeys("invalid");
        driver.findElement(By.id("password")).sendKeys("invalid");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify error message
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("Your username is invalid!"));
    }

    @Test
    @DisplayName("Test form clearing")
    void testFormClearing() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Enter text
        usernameField.sendKeys("some text");
        assertEquals("some text", usernameField.getAttribute("value"));

        // Clear field
        usernameField.clear();
        assertEquals("", usernameField.getAttribute("value"));

        // Enter new text
        usernameField.sendKeys("new text");
        assertEquals("new text", usernameField.getAttribute("value"));
    }

    @Test
    @DisplayName("Test form clearing")
    void testLogout() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));

        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        logoutButton.click();

        flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged out of the secure area!"));
    }

    @Test
    @DisplayName("Test form clearing")
    void testLoginUsingEnter() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        passwordField.sendKeys(Keys.ENTER);

        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Test form clearing")
    void testDefaultFormValues() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        assertEquals("", usernameField.getAttribute("value"));

        WebElement passwordField = driver.findElement(By.id("password"));
        assertEquals("", passwordField.getAttribute("value"));

    }

    @Test
    @DisplayName("Test link clicking and navigation")
    void testLinkClicking() {
        driver.get("https://the-internet.herokuapp.com/");

        // Click a link
        driver.findElement(By.linkText("Checkboxes")).click();

        // Verify navigation
        assertTrue(driver.getCurrentUrl().contains("checkboxes"));

        // Go back
        driver.navigate().back();

        // Verify we're back
        assertTrue(driver.getCurrentUrl().equals("https://the-internet.herokuapp.com/"));
    }

    @Test
    @DisplayName("Test checkbox interactions")
    void testCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Find checkboxes
        java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        assertEquals(2, checkboxes.size(), "Should find 2 checkboxes");

        // Check states
        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);

        // First checkbox - initially unchecked
        assertFalse(checkbox1.isSelected());

        // Click to check
        checkbox1.click();
        assertTrue(checkbox1.isSelected());

        // Click to uncheck
        checkbox1.click();
        assertFalse(checkbox1.isSelected());
    }

    @Test
    @DisplayName("Test getting element attributes")
    void testGetAttributes() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Get various attributes
        String id = usernameField.getAttribute("id");
        String type = usernameField.getAttribute("type");
        String name = usernameField.getAttribute("name");

        assertEquals("username", id);
        assertEquals("text", type);
        assertEquals("username", name);

        // Check if element is enabled and displayed
        assertTrue(usernameField.isEnabled());
        assertTrue(usernameField.isDisplayed());
    }
}
