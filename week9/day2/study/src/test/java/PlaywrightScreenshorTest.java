
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotAnimations;
import com.microsoft.playwright.options.ScreenshotType;

public class PlaywrightScreenshorTest extends BaseTest {

    @Test
    void basicScreenshot() {
        page.navigate("https://example.com");

        // Simple screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot.png"))
        );
    }

    @Test
    void screenshotWithOptions() {
        page.navigate("https://example.com");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/page.png"))
                .setFullPage(true)
                .setType(ScreenshotType.PNG)
                .setOmitBackground(false)
                .setAnimations(ScreenshotAnimations.DISABLED)
                .setTimeout(30000)
        );
    }

    // @Test
    // void elementScreenshot() {
    //     page.navigate("https://example.com");
    //     // Screenshot of specific element
    //     page.locator("#header").screenshot(new Locator.ScreenshotOptions()
    //             .setPath(Paths.get("screenshots/header.png"))
    //     );
    //     // Element with options
    //     page.locator(".product-card").first().screenshot(new Locator.ScreenshotOptions()
    //             .setPath(Paths.get("screenshots/product.png"))
    //             .setOmitBackground(true) // Transparent background
    //             .setAnimations(ScreenshotAnimations.DISABLED)
    //     );
    // }
    @Test
    void fullPageScreenshot() {
        page.navigate("https://example.com/long-page");

        // Captures entire scrollable page
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/full-page.png"))
                .setFullPage(true)
        );
    }
}
