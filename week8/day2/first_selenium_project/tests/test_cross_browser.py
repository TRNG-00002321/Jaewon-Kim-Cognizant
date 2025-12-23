import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import sys
import os
from pathlib import Path
sys.path.insert(0, '..')
from utils.multi_browser_factory import create_driver

BROWSERS = ["chrome"] 


@pytest.mark.parametrize("browser", BROWSERS)
def test_page_loads_correctly(browser):
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        
        assert "The Internet" in driver.title
        
        heading = driver.find_element(By.TAG_NAME, "h1")
        assert "Welcome to the-internet" in heading.text


@pytest.mark.parametrize("browser", BROWSERS)
def test_form_interaction(browser):
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/login")
        
        username_field = driver.find_element(By.ID, "username")
        username_field.send_keys("tomsmith")
        
        password_field = driver.find_element(By.ID, "password")
        password_field.send_keys("SuperSecretPassword!")
        
        login_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
        login_button.click()
        
        wait = WebDriverWait(driver, 10)
        flash_message = wait.until(
            EC.presence_of_element_located((By.CLASS_NAME, "flash"))
        )
        
        assert "You logged into a secure area!" in flash_message.text


@pytest.mark.parametrize("browser", BROWSERS)
def test_screenshot_capture(browser):
    screenshots_dir = Path("screenshots")
    screenshots_dir.mkdir(exist_ok=True)
    
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        
        screenshot_path = screenshots_dir / f"{browser}_screenshot.png"
        driver.save_screenshot(str(screenshot_path))
    
        assert screenshot_path.exists(), f"Screenshot file not found: {screenshot_path}"
        assert screenshot_path.stat().st_size > 0, f"Screenshot file is empty"