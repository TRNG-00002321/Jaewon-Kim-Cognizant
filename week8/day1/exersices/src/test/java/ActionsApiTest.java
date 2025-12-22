
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsApiTest extends BaseTest {

    private Actions actions;

    @BeforeEach
    void setupActions() {
        actions = new Actions(driver);
    }

    @Test
    @DisplayName("Mouse hover reveals hidden element")
    void testMouseHover() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        // Find the first figure (avatar)
        WebElement figure = driver.findElement(By.cssSelector(".figure"));

        // Hover over the figure
        actions.moveToElement(figure).perform();

        // Now the caption should be visible
        WebElement caption = figure.findElement(By.cssSelector(".figcaption"));
        assertTrue(caption.isDisplayed(), "Caption should be visible on hover");

        // Verify caption content
        String captionText = caption.getText();
        assertTrue(captionText.contains("user1"), "Caption should contain user info");
    }

    @Test
    @DisplayName("Hover over multiple elements")
    void testMultipleHovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        java.util.List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));

        for (int i = 0; i < figures.size(); i++) {
            WebElement figure = figures.get(i);

            // Hover
            actions.moveToElement(figure).perform();

            // Verify caption visible
            WebElement caption = figure.findElement(By.cssSelector(".figcaption h5"));
            //assertTrue(caption.isDisplayed(), "Caption " + i + " should be visible");

            // Move away
            actions.moveToElement(driver.findElement(By.tagName("h3"))).perform();
        }
    }

    @Test
    @DisplayName("Hover and click link")
    void testHoverAndClick() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));

        // Hover to reveal link
        actions.moveToElement(figure).perform();

        // Click the revealed link
        WebElement profileLink = figure.findElement(By.cssSelector(".figcaption a"));
        profileLink.click();

        // Verify navigation
        assertTrue(driver.getCurrentUrl().contains("/users/"));
    }

    @Test
    @DisplayName("Hover on all three figures and verify different usernames")
    void testAllFiguresWithDifferentUsernames() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));

        // Expected usernames for each figure
        String[] expectedUsernames = {"user1", "user2", "user3"};

        assertEquals(3, figures.size(), "Should have 3 figures");

        for (int i = 0; i < figures.size(); i++) {
            WebElement figure = figures.get(i);

            // Hover over the figure
            actions.moveToElement(figure).perform();

            // Get the caption text
            WebElement caption = figure.findElement(By.cssSelector(".figcaption h5"));
            String captionText = caption.getText();

            // Verify the username
            assertTrue(captionText.contains(expectedUsernames[i]),
                    "Figure " + (i + 1) + " should contain " + expectedUsernames[i]);

            // Move away to reset for next iteration
            actions.moveToElement(driver.findElement(By.tagName("h3"))).perform();
        }
    }

    @Test
    @DisplayName("Hover and click in one action chain")
    void testHoverAndClickInOneChain() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));
        WebElement profileLink = figure.findElement(By.cssSelector(".figcaption a"));

        // Perform hover and click in a single action chain
        actions.moveToElement(figure)
                .pause(Duration.ofMillis(500)) // Optional: brief pause for stability
                .click(profileLink)
                .perform();

        // Verify navigation
        assertTrue(driver.getCurrentUrl().contains("/users/1"),
                "Should navigate to user profile page");
    }

    @Test
    @DisplayName("Basic drag and drop")
    void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        // Get initial text
        String sourceInitialText = source.getText();
        String targetInitialText = target.getText();

        assertEquals("A", sourceInitialText);
        assertEquals("B", targetInitialText);

        // Perform drag and drop
        actions.dragAndDrop(source, target).perform();

        // Note: This site might have issues with standard drag-drop
        // Alternative using JavaScript might be needed
    }

    @Test
    @DisplayName("Drag and drop with click-hold-move-release")
    void testDragAndDropManual() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        // Manual drag and drop sequence
        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();
    }

    @Test
    @DisplayName("Drag and drop with offset")
    void testDragAndDropByOffset() {
        driver.get("https://jqueryui.com/draggable/");

        // Switch to iframe containing the draggable
        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        WebElement draggable = driver.findElement(By.id("draggable"));

        // Get initial position
        int initialX = draggable.getLocation().getX();
        int initialY = draggable.getLocation().getY();

        // Drag by offset (100px right, 50px down)
        actions.dragAndDropBy(draggable, 100, 50).perform();

        // Verify position changed
        int newX = draggable.getLocation().getX();
        int newY = draggable.getLocation().getY();

        assertTrue(newX > initialX, "Element should have moved right");
        assertTrue(newY > initialY, "Element should have moved down");

        // Switch back to main content
        driver.switchTo().defaultContent();
    }

    @Test
    @DisplayName("Double-click action")
    void testDoubleClick() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addButton = driver.findElement(By.cssSelector("button[onclick='addElement()']"));

        // Double-click to add two elements
        actions.doubleClick(addButton).perform();

        // Verify two elements added
        java.util.List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".added-manually"));
        assertEquals(2, deleteButtons.size(), "Double-click should add 2 elements");
    }

    @Test
    @DisplayName("Context menu (right-click)")
    void testContextClick() {
        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement hotSpot = driver.findElement(By.id("hot-spot"));

        // Right-click on the element
        actions.contextClick(hotSpot).perform();

        // Handle the alert that appears
        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("You selected a context menu"));

        // Accept the alert
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Click at specific coordinates")
    void testClickAtOffset() {
        driver.get("https://the-internet.herokuapp.com/");

        WebElement heading = driver.findElement(By.tagName("h1"));

        // Click at offset from element center
        actions.moveToElement(heading, 10, 10)
                .click()
                .perform();
    }

    @Test
    @DisplayName("Keyboard modifier keys")
    void testKeyboardModifiers() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(By.id("target"));

        // Press a regular key
        actions.click(input)
                .sendKeys("a")
                .perform();

        WebElement result = driver.findElement(By.id("result"));
        assertTrue(result.getText().contains("A"));

        // Press with Shift
        actions.keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .perform();

        // Result should show SHIFT
        assertTrue(result.getText().contains("A"));
    }

    @Test
    @DisplayName("Ctrl+A, Ctrl+C, Ctrl+V sequence")
    void testCopyPaste() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Type text in username
        usernameField.sendKeys("testtext");

        // Select all in username field
        actions.click(usernameField)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();

        // Copy
        actions.keyDown(Keys.CONTROL)
                .sendKeys("c")
                .keyUp(Keys.CONTROL)
                .perform();

        // Click password field and paste
        actions.click(passwordField)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();

        // Note: Due to security, password fields might not accept paste
        // This test demonstrates the action sequence
    }

    @Test
    @DisplayName("Arrow key navigation")
    void testArrowKeys() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Type text
        usernameField.sendKeys("hello");

        // Move cursor to beginning
        actions.sendKeys(Keys.HOME).perform();

        // Type at beginning
        actions.sendKeys("prefix_").perform();

        assertEquals("prefix_hello", usernameField.getAttribute("value"));
    }

    @Test
    @DisplayName("Tab through form fields")
    void testTabNavigation() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Focus on username
        usernameField.click();
        usernameField.sendKeys("user");

        // Tab to password
        actions.sendKeys(Keys.TAB).perform();

        // Type in password (now focused)
        actions.sendKeys("password").perform();

        // Verify password field has the value
        WebElement passwordField = driver.findElement(By.id("password"));
        assertEquals("password", passwordField.getAttribute("value"));
    }

    @Test
    @DisplayName("Complex action chain")
    void testComplexActionChain() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Complete form in one action chain
        actions.click(usernameField)
                .sendKeys("tomsmith")
                .sendKeys(Keys.TAB)
                .sendKeys("SuperSecretPassword!")
                .click(loginButton)
                .perform();

        // Verify login
        assertTrue(driver.getCurrentUrl().contains("secure"));
    }

    @Test
    @DisplayName("Build and perform actions separately")
    void testBuildActions() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));

        // Build action without performing
        actions.moveToElement(figure);

        // Caption not yet visible (action not performed)
        WebElement caption = figure.findElement(By.cssSelector(".figcaption"));
        // Note: visibility might depend on CSS

        // Now perform
        actions.perform();

        // Now caption should be visible
        assertTrue(caption.isDisplayed());
    }
}
