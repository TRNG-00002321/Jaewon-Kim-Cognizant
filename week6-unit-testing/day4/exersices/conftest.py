import json
import pytest
from pathlib import Path


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