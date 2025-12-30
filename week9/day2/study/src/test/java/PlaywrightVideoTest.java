
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightVideoTest extends BaseTest {

    @Test
    void recordVideo() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();

            // Enable video recording in context
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("videos/"))
            );

            Page page = context.newPage();
            page.navigate("https://example.com");
            page.locator("#login").click();

            // Video is saved when context closes
            context.close();

            browser.close();
        }
    }

    @Test
    void recordVideoWithOptions() {
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                // Video directory
                .setRecordVideoDir(Paths.get("videos/"))
                // Video dimensions
                .setRecordVideoSize(1280, 720)
        );

        Page page = context.newPage();

        // Run test...
        page.navigate("https://example.com");

        // Get video path (available after page closes)
        page.close();
        Path videoPath = page.video().path();
        System.out.println("Video saved: " + videoPath);

        context.close();
    }

    @Test
    void saveVideoWithCustomName() {
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
        );

        Page page = context.newPage();
        page.navigate("https://example.com");

        // Close page to finalize video
        page.close();

        // Save with custom name
        Path originalPath = page.video().path();
        Path customPath = Paths.get("videos/my_test_"
                + System.currentTimeMillis() + ".webm");

        page.video().saveAs(customPath);

        // Delete original if needed
        page.video().delete();

        context.close();
    }
}
