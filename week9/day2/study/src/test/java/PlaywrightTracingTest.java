
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class PlaywrightTracingTest {

    @Test
    void recordTrace() {
        try (Playwright playwright = Playwright.create(); Browser browser = playwright.chromium().launch(); BrowserContext context = browser.newContext()) {

            // Start tracing
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true) // Capture screenshots
                    .setSnapshots(true) // Capture DOM snapshots
                    .setSources(true) // Include source code
            );

            Page page = context.newPage();
            page.navigate("https://the-internet.herokuapp.com/login");
            page.locator("#username").fill("tomsmith");
            page.locator("#password").fill("SuperSecretPassword!");
            page.locator(".radius").click();

            // Stop and save trace
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("./target/trace.zip"))
            );

        }
    }

    @DisplayName("Advance Tracing")
    @Test
    public void demoAdvancedTracing() {
        try (Playwright playwright = Playwright.create(); Browser browser = playwright.chromium().launch(); BrowserContext context = browser.newContext()) {

            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true)
                    .setTitle("Login Flow Test")
            );
            System.out.println("   ✓ Tracing started with title");

            Page page = context.newPage();

            page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("   → Test scenario 1: Homepage");

            context.tracing().startChunk();

            page.locator("a:has-text('Form Authentication')").click();
            page.locator("#username").fill("tomsmith");
            page.locator("#password").fill("SuperSecretPassword!");
            page.locator("button[type='submit']").click();

            context.tracing().stopChunk(new Tracing.StopChunkOptions()
                    .setPath(Paths.get("target/trace-login.zip"))
            );
            System.out.println("   ✓ Login chunk saved: trace-login.zip");

            context.tracing().startChunk();

            page.locator("a[href='/logout']").click();
            page.waitForURL("**/login");

            context.tracing().stopChunk(new Tracing.StopChunkOptions()
                    .setPath(Paths.get("target/trace-logout.zip"))
            );
            System.out.println("   ✓ Logout chunk saved: trace-logout.zip");

            context.tracing().stop();
        }

        System.out.println();
    }
}
