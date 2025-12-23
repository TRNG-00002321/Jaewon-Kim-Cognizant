from contextlib import contextmanager
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from webdriver_manager.core.os_manager import ChromeType

@contextmanager
def create_chrome_driver(headless: bool = False):
    options = webdriver.ChromeOptions()
    if headless:
        #options.add_argument('--headless')
        options.add_argument('--disable-gpu')
        options.add_argument('--no-sandbox')   

    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service, options=options)
    try:
        yield driver
    finally:
        driver.quit()