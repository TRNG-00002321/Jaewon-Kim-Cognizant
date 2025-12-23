"""
Locator Strategy Mastery Tests

Demonstrate all 8 locator strategies and compare their usage.
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import pytest


@pytest.fixture
def driver():
    """Create a Chrome driver for testing."""
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    driver.implicitly_wait(10)
    driver.get("https://the-internet.herokuapp.com/login")
    yield driver
    driver.quit()


class TestLocatorStrategies:
    """
    Test all 8 locator strategies on the login page.
    
    Page Elements to locate:
    - Username input: <input type="text" name="username" id="username">
    - Password input: <input type="password" name="password" id="password">
    - Login button: <button type="submit" class="radius">Login</button>
    - Page heading: <h2>Login Page</h2>
    - Subheading link: <a href="...">Elemental Selenium</a>
    """
    
    def test_locate_by_id(self, driver):
        """Locate elements by ID attribute."""
        username = driver.find_element(By.ID, "username")
        assert username.is_displayed()
        
        password = driver.find_element(By.ID, "password")
        assert password.is_displayed()
    
    def test_locate_by_name(self, driver):
        """Locate elements by name attribute."""
        username = driver.find_element(By.NAME, "username")
        assert username.is_displayed()
    
    def test_locate_by_class_name(self, driver):
        """Locate elements by class name."""
        button = driver.find_element(By.CLASS_NAME, "radius")
        assert button.is_displayed()
    
    def test_locate_by_tag_name(self, driver):
        """Locate elements by tag name."""
        inputs = driver.find_elements(By.TAG_NAME, "input")
        assert len(inputs) >= 2
    
    def test_locate_by_link_text(self, driver):
        """Locate anchor elements by exact link text."""
        link = driver.find_element(By.LINK_TEXT, "Elemental Selenium")
        assert link.is_displayed()
    
    def test_locate_by_partial_link_text(self, driver):
        """Locate anchor elements by partial link text."""
        link = driver.find_element(By.PARTIAL_LINK_TEXT, "Elemental")
        assert link.is_displayed()
    
    def test_locate_by_css_selector(self, driver):
        """Locate elements by CSS selector."""
        username_by_id = driver.find_element(By.CSS_SELECTOR, "#username")
        assert username_by_id.is_displayed()
        
        button_by_class = driver.find_element(By.CSS_SELECTOR, ".radius")
        assert button_by_class.is_displayed()
        
        username_compound = driver.find_element(By.CSS_SELECTOR, "input[name='username'][id='username']")
        assert username_compound.is_displayed()
        
        form_input = driver.find_element(By.CSS_SELECTOR, "form input")
        assert form_input.is_displayed()
        
        password_by_type = driver.find_element(By.CSS_SELECTOR, "input[type='password']")
        assert password_by_type.is_displayed()
    
    def test_locate_by_xpath(self, driver):
        """Locate elements by XPath."""
        username_xpath = driver.find_element(By.XPATH, "//input[@id='username']")
        assert username_xpath.is_displayed()
        
        heading = driver.find_element(By.XPATH, "//h2[text()='Login Page']")
        assert heading.is_displayed()
        
        link_contains = driver.find_element(By.XPATH, "//a[contains(text(), 'Elemental')]")
        assert link_contains.is_displayed()
        
        password_following = driver.find_element(By.XPATH, "//input[@id='username']/following::input[@id='password']")
        assert password_following.is_displayed()
        
        form_parent = driver.find_element(By.XPATH, "//input[@id='username']/ancestor::form")
        assert form_parent.is_displayed()


class TestXPathAdvanced:
    """Advanced XPath exercises."""
    
    def test_xpath_contains(self, driver):
        """
        Use contains() for partial attribute matching.
        Find elements where attribute contains specific text.
        """
        button = driver.find_element(By.XPATH, "//button[contains(., 'Login')]")
        assert button.is_displayed()
    
    def test_xpath_starts_with(self, driver):
        """
        Use starts-with() for prefix matching.
        Useful for dynamic IDs like "user_12345".
        """
        username = driver.find_element(By.XPATH, "//input[starts-with(@id, 'user')]")
        assert username.is_displayed()
    
    def test_xpath_text_functions(self, driver):
        """
        Use text(), normalize-space() for text matching.
        """
        heading = driver.find_element(By.XPATH, "//h2[text()='Login Page']")
        assert heading.is_displayed()
        
        heading_normalized = driver.find_element(By.XPATH, "//h2[normalize-space()='Login Page']")
        assert heading_normalized.is_displayed()
    
    def test_xpath_axes(self, driver):
        """
        Use XPath axes for relative element location.
        """
        password_following = driver.find_element(By.XPATH, "//input[@id='username']/following::input[@id='password']")
        assert password_following.is_displayed()
        
        username_preceding = driver.find_element(By.XPATH, "//input[@id='password']/preceding::input[@id='username']")
        assert username_preceding.is_displayed()
        
        form_parent = driver.find_element(By.XPATH, "//input[@id='username']/ancestor::form")
        assert form_parent.is_displayed()
        
        form_ancestor = driver.find_element(By.XPATH, "//button[@type='submit']/ancestor::form")
        assert form_ancestor.is_displayed()