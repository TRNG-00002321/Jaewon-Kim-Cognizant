package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement usernameField; 

    @FindBy(id = "password")
    private WebElement passwordField; 

    @FindBy(xpath = "//i[@class='fa fa-2x fa-sign-in']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
