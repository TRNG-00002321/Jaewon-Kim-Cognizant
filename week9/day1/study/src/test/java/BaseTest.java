
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public abstract class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void globalSetup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(System.getenv("CI") != null)
        );
    }

    @AfterAll
    static void globalTeardown() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
        );
        page = context.newPage();
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        // Screenshot on failure
        if (testInfo.getTags().contains("screenshot-on-failure")) {
            // Capture logic here
        }
        context.close();
    }

    protected void navigateTo(String path) {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "https://the-internet.herokuapp.com");
        page.navigate(baseUrl + path);
    }
}
