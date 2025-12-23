from selenium.webdriver.common.by import By
import sys
sys.path.insert(0, '..')
from utils.driver_factory import create_chrome_driver

def test_page_title():
    """Verify the page title matches expected value."""
    with create_chrome_driver(headless=False) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        assert driver.title == "The Internet", f"Expected 'The Internet', got '{driver.title}'"

def test_heading_text():
    """Verify the main heading contains expected text."""
    with create_chrome_driver(headless=False) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        heading = driver.find_element(By.TAG_NAME, "h1")
        assert "Welcome to the-internet" in heading.text, f"Expected heading to contain 'Welcome to the-internet', got '{heading.text}'"

def test_links_present():
    """Verify that all example links are present on the page."""
    with create_chrome_driver(headless=False) as driver:
        driver.get("https://the-internet.herokuapp.com/")

        links = driver.find_elements(By.CSS_SELECTOR, "ul li a")
        link_texts = [link.text for link in links]
        
        assert len(link_texts) > 0, "No links found on the page"

def test_link_attributes():
    """Verify that links have correct href attributes."""
    with create_chrome_driver(headless=False) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        links = driver.find_elements(By.CSS_SELECTOR, "ul li a")
        
        for link in links:
            href = link.get_attribute("href")
            assert href is not None, f"Link '{link.text}' has no href attribute"
            assert href.startswith("https://the-internet.herokuapp.com/"), \
                f"Link '{link.text}' has invalid href: {href}"
