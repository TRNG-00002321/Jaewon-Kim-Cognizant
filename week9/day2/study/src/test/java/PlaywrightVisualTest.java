
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

public class PlaywrightVisualTest {

    public void testVisuals() {
        try (Playwright playwright = Playwright.create(); Browser browser = playwright.chromium().launch();) {
            Page page = browser.newPage();

            page.navigate("https://the-internet.herokuapp.com/login");

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Path baselinePath = Paths.get("targetvisuals/baseline/login-page.png");
            //     page.screenshot(New Page

            // );
        }
    }
}
