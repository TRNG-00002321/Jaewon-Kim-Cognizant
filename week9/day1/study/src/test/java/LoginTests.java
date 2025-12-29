
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTests extends BaseTest {

    @Test
    void shouldLoginSuccessfully() {
        navigateTo("/login");

        page.locator("#username").fill("tomsmith");
        page.locator("#password").fill("SuperSecretPassword!");
        page.locator(".radius").click();

        assertThat(page).hasURL(Pattern.compile(".*secure"));
    }

    @Test
    void shouldShowErrorForInvalidCredentials() {
        navigateTo("/login");

        page.locator("#username").fill("invalid");
        page.locator("#password").fill("wrong");
        page.locator(".radius").click();

        assertThat(page.locator("#flash")).isVisible();
        assertThat(page.locator("#flash")).containsText("invalid!");
    }
}
