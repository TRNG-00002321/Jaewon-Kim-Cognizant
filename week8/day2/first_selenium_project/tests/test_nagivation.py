from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import sys
import os
sys.path.insert(0, '..')
from utils.driver_factory import create_chrome_driver

def test_navigate_to_login_page():
    """
    Test: Navigate from home to login page
    
    Steps:
    1. Go to the-internet homepage
    2. Find and click "Form Authentication" link
    3. Assert URL contains "/login"
    4. Assert page contains "Login Page" heading
    """
    with create_chrome_driver(headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        print(f"Initial URL: {driver.current_url}")
        
        form_auth_link = driver.find_element(By.LINK_TEXT, "Form Authentication")
        form_auth_link.click()
        
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.TAG_NAME, "h2"))
        )
        
        current_url = driver.current_url
        assert "/login" in current_url, f"Expected '/login' in URL, got: {current_url}"
        print(f"✓ URL changed to: {current_url}")
        
        heading = driver.find_element(By.TAG_NAME, "h2")
        assert "Login Page" in heading.text, f"Expected 'Login Page', got: {heading.text}"

def test_back_forward_navigation():
    """
    Test: Browser navigation (back/forward)
    
    Steps:
    1. Navigate to homepage
    2. Click a link to go to another page
    3. Use driver.back() to return
    4. Assert you're on homepage
    5. Use driver.forward() to go forward
    6. Assert you're on the second page again
    """
    with create_chrome_driver(headless=True) as driver:
        base_url = "https://the-internet.herokuapp.com/"
        
        driver.get(base_url)
        print(f"Starting at: {driver.current_url}")
        
        form_auth_link = driver.find_element(By.LINK_TEXT, "Form Authentication")
        form_auth_link.click()
        
        WebDriverWait(driver, 10).until(
            EC.url_contains("/login")
        )
        login_url = driver.current_url
        print(f"Navigated to: {login_url}") 
        driver.back()
        
        WebDriverWait(driver, 10).until(
            EC.url_to_be(base_url)
        )
        assert driver.current_url == base_url, f"Expected {base_url}, got: {driver.current_url}"
        driver.forward()
        
        WebDriverWait(driver, 10).until(
            EC.url_contains("/login")
        )
        assert "/login" in driver.current_url, f"Expected '/login' in URL, got: {driver.current_url}"


def test_capture_screenshot():
    """
    Test: Screenshot capture
    
    Steps:
    1. Navigate to any page
    2. Take a full page screenshot
    3. Save it to screenshots/homepage.png
    """
    with create_chrome_driver(headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        print(f"Navigated to: {driver.current_url}")
        
        screenshots_dir = "screenshots"
        if not os.path.exists(screenshots_dir):
            os.makedirs(screenshots_dir)
            print(f"✓ Created directory: {screenshots_dir}")
        
        screenshot_path = os.path.join(screenshots_dir, "homepage.png")
        driver.save_screenshot(screenshot_path)
        
        assert os.path.exists(screenshot_path), f"Screenshot not found at: {screenshot_path}"
        file_size = os.path.getsize(screenshot_path)
        print(f"✓ Screenshot saved: {screenshot_path} ({file_size} bytes)")
        
        print("✓ test_capture_screenshot PASSED\n")