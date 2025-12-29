
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;

public class PlaywrightInteractionsTest extends BaseTest {

    @Test
    public void waitTest() {
        navigateTo("/dynamic_loading/1");
        page.locator("text=Start").click();

        String result = page.locator("#finish h4").textContent();
        assert result.equals("Hello World!");
    }

    @Test
    public void demoLocatorsTest() {
        navigateTo("/login");
        Locator byId = page.locator("#username");
        Locator byText = page.locator("text=Login");
        Locator byRole = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")); //ARIA = Accessible Rich Internet Applications
        Locator byPlaceholder = page.getByPlaceholder("Username");
        Locator byLabel = page.getByLabel("Username");
    }

    @Test
    public void interactionsTest() {
        navigateTo("/login");
        page.locator("#username").fill("tomsmith");
        page.locator("#password").fill("SuperSecretPassword!");
        page.locator(".radius").click();
        assertThat(page).hasURL(Pattern.compile(".*secure"));
        assertThat(page.locator("#flash")).containsText("secure area");
    }
}
