import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@DisplayName("Basic Selenium Test")
public class SeleniumBasicsTest {
    private WebDriver webDriver;

    @Test
    public void testBasic() throws InterruptedException{
        //Setup Chrome Driver
        WebDriverManager.chromiumdriver().setup();

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        //Nagivae to website
        webDriver.get("https://www.selenium.dev/");
        Thread.sleep(5000);

        //Get the title
        String title = webDriver.getTitle();
        System.out.print(title);
    }
}
