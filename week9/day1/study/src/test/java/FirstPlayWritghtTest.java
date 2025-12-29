
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@DisplayName("First PlayWright Test")
public class FirstPlayWritghtTest {

    @DisplayName("Sample Test Case")
    @Test
    public void sampleTest() {
        try (Playwright playwright = Playwright.create(); Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(500)
        )) {
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            String title = page.title();
            assert title.equals("Fast and reliable end-to-end testing for modern web apps | Playwright");
        }
    }

    @DisplayName("Sample Test Case")
    @Test
    public void clickTest() {
        try (Playwright playwright = Playwright.create(); Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(500)
        )) {
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");

            page.locator("text=Get started").click();
            assertThat(page).hasURL(Pattern.compile(".*intro"));

        }
    }
}
