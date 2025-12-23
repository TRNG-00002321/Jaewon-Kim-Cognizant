from contextlib import contextmanager
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.edge.service import Service as EdgeService
from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.edge.options import Options as EdgeOptions
from webdriver_manager.chrome import ChromeDriverManager
from webdriver_manager.firefox import GeckoDriverManager
from webdriver_manager.microsoft import EdgeChromiumDriverManager
from webdriver_manager.core.os_manager import ChromeType


@contextmanager
def create_driver(browser: str = "chrome", headless: bool = False):
    driver = None
    
    try:
        if browser.lower() == "chrome":
            options = ChromeOptions()
            
            if headless:
                options.add_argument('--headless=new')
            
            options.add_argument('--no-sandbox')
            options.add_argument('--disable-dev-shm-usage')
            options.add_argument('--disable-gpu')
            
            service = ChromeService(ChromeDriverManager(chrome_type=ChromeType.CHROMIUM).install())
            driver = webdriver.Chrome(service=service, options=options)
            
        elif browser.lower() == "firefox":
            options = FirefoxOptions()
            
            if headless:
                options.add_argument('-headless')
            
            service = FirefoxService(GeckoDriverManager().install())
            driver = webdriver.Firefox(service=service, options=options)
            
        elif browser.lower() == "edge":
            options = EdgeOptions()
            if headless:
                options.add_argument('--headless=new')
            
            options.add_argument('--no-sandbox')
            options.add_argument('--disable-dev-shm-usage')
            options.add_argument('--disable-gpu')
            
            service = EdgeService(EdgeChromiumDriverManager().install())
            driver = webdriver.Edge(service=service, options=options)
            
        else:
            raise ValueError(f"Unsupported browser: {browser}")
        
        driver.implicitly_wait(10)
        yield driver
        
    finally:
        if driver:
            driver.quit()


def get_browser_version(browser: str) -> str:
    import subprocess
    import re
    
    try:
        if browser.lower() == "chrome":
            commands = [
                ["chromium-browser", "--version"],
                ["chromium", "--version"],
                ["google-chrome", "--version"],
                ["google-chrome-stable", "--version"]
            ]
            
        elif browser.lower() == "firefox":
            commands = [
                ["firefox", "--version"],
                ["firefox-esr", "--version"]
            ]
            
        elif browser.lower() == "edge":
            commands = [
                ["microsoft-edge", "--version"],
                ["microsoft-edge-stable", "--version"]
            ]
        else:
            return f"Unknown browser: {browser}"
        
        for command in commands:
            try:
                result = subprocess.run(
                    command,
                    capture_output=True,
                    text=True,
                    timeout=5
                )
                if result.returncode == 0:
                    output = result.stdout.strip()
                    version_match = re.search(r'(\d+\.[\d.]+)', output)
                    if version_match:
                        return version_match.group(1)
                    return output
            except (FileNotFoundError, subprocess.TimeoutExpired):
                continue
        
        return f"{browser.capitalize()} not found or version unavailable"
        
    except Exception as e:
        return f"Error detecting {browser} version: {str(e)}"