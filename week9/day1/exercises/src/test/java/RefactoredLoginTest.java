
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;

public class RefactoredLoginTest {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true));
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContext() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    @DisplayName("Complete login and logout flow")
    void testLoginLogoutFlow() {
        // Navigate to home page
        page.navigate("https://the-internet.herokuapp.com/");

        // Click login link - using accessible role
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Form Authentication")).click();

        // Verify on login page
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");

        // Fill login form - removed redundant click()
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");

        // Submit login
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")).click();

        // Verify successful login
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/secure"));
        assertThat(page.locator("#flash")).containsText("You logged into a secure area!");

        // Logout
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Logout")).click();

        // Verify logout
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/login"));
        assertThat(page.locator("#flash")).containsText("You logged out");
    }
}
