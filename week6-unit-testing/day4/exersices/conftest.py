import json
import pytest
from pathlib import Path
from models import User, Database


@pytest.fixture(scope="session")
def test_data():
    """Load all test data from JSON file."""
    data_path = Path(__file__).parent / "calculator_tests.json"
    with open(data_path) as f:
        return json.load(f)


def load_test_cases(filename, key):
    """Helper function to load specific test cases."""
    data_path = Path(__file__).parent / filename
    with open(data_path) as f:
        data = json.load(f)
    return data.get(key, [])

def pytest_generate_tests(metafunc):
    """Generate tests dynamically based on fixture names."""
    
    if "email_test_case" in metafunc.fixturenames:
        cases = load_test_cases("user_validation_tests.json", "valid_emails")
        cases += load_test_cases("user_validation_tests.json", "invalid_emails")
        metafunc.parametrize(
            "email_test_case",
            cases,
            ids=[c["description"] for c in cases]
        )
        
@pytest.fixture
def user():
    """Create a standard test user."""
    return User(
        id=1,
        username="testuser",
        email="test@example.com",
        role="user"
    )


@pytest.fixture
def admin_user():
    """Create an admin test user."""
    return User(
        id=99,
        username="admin",
        email="admin@example.com",
        role="admin"
    )
    
