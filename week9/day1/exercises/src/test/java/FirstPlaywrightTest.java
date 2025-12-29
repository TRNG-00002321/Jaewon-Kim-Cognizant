
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * First Playwright test demonstrating basic operations.
 */
public class FirstPlaywrightTest {

    // Shared across all tests in the class
    static Playwright playwright;
    static Browser browser;

    // Unique per test for isolation
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Set to true for CI
                .setSlowMo(100));    // Slow down for visibility (remove in production)
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testNavigateToHomepage() {
        // Navigate to page
        page.navigate("https://the-internet.herokuapp.com/");

        // Playwright's web-first assertions automatically wait!
        assertThat(page).hasTitle("The Internet");

        // Find heading and assert
        Locator heading = page.locator("h1");
        assertThat(heading).containsText("Welcome to the-internet");
    }

    @Test
    void testClickNavigationLink() {
        page.navigate("https://the-internet.herokuapp.com/");

        // Click link - auto-waits for element to be actionable
        page.click("text=Form Authentication");

        // Assert URL changed
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");

        // Assert heading on new page
        assertThat(page.locator("h2")).hasText("Login Page");
    }

    @Test
    void testFormInteraction() {
        page.navigate("https://the-internet.herokuapp.com/login");

        // Fill form - auto-waits for fields
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");

        // Click login
        page.click("button[type='submit']");

        // Assert redirect and success message
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/secure"));
        assertThat(page.locator("#flash")).containsText("You logged into");
    }

    @Test
    void testAutoWaitBehavior() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Click start - element hidden initially
        page.click("#start button");

        // This automatically waits for element to be visible!
        // No explicit wait needed unlike Selenium
        Locator result = page.locator("#finish h4");

        // Web-first assertion waits until condition is met
        assertThat(result).hasText("Hello World!");
    }

    @Test
    void testLocatorStrategies() {
        page.navigate("https://the-internet.herokuapp.com/login");

        // By ID
        Locator byId = page.locator("#username");
        assertThat(byId).isVisible();

        // By CSS
        Locator byCss = page.locator("input[name='password']");
        assertThat(byCss).isVisible();

        // // By text
        // Locator byText = page.locator("text=Login");
        // assertThat(byText).isVisible();
        // // By role (accessibility)
        // Locator byRole = page.getByRole(AriaRole.BUTTON,
        //         new Page.GetByRoleOptions().setName("Login"));
        // assertThat(byRole).isVisible();
        // By placeholder
        Locator byPlaceholder = page.getByPlaceholder("username");
        // This might not exist on this page - just showing the API
    }
}
