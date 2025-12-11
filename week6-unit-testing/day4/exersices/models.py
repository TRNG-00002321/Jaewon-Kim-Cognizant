import json
from pathlib import Path
from typing import Any, Dict, List, Optional

class User:
    def __init__(self, id: int, username: str, email: str, role: str):
        self.id = id
        self.username = username
        self.email = email
        self.role = role

class Database:
    """Simple database class for testing."""
    
    def __init__(self):
        """Initialize an empty in-memory database."""
        self.users = []
        self.test_data = None
    
    def save(self, user) -> None:
        """Save a user to the database."""
        self.users.append(user)
    
    def delete(self, user) -> None:
        """Delete a user from the database."""
        if user in self.users:
            self.users.remove(user)
    
    def load_test_data(self, filename: str) -> Dict[str, Any]:
        """Load test data from a JSON file."""
        data_path = Path(__file__).parent / filename
        with open(data_path) as f:
            self.test_data = json.load(f)
        return self.test_data
    
    def clear_test_data(self) -> None:
        """Clear all test data."""
        self.test_data = None
        self.users.clear()