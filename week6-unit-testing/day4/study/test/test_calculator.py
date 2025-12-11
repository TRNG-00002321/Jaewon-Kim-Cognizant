# test_calculator.py
import unittest
from src.calculator import Calculator

class TestCalculator(unittest.TestCase):

    def setUp(self):
        """Set up a new Calculator instance before each test."""
        self.calc = Calculator()
        print("setUp called: Calculator instance created.")

    def tearDown(self):
        """Clean up resources after each test (optional for simple objects)."""
        # For more complex objects, you might reset attributes or close connections.
        self.calc = None # Explicitly dereference for clarity, though not strictly necessary for simple objects.
        print("tearDown called: Calculator instance cleaned up.")

    @unittest.skip("Demonstrating unconditional skipping")
    def test_add_positive_numbers(self):
        """Test adding two positive numbers."""
        result = self.calc.add(5, 3)
        self.assertEqual(result, 8)
        print("  test_add_positive_numbers executed.")

    def test_subtract_numbers(self):
        """Test subtracting numbers."""
        result = self.calc.subtract(10, 4)
        self.assertEqual(result, 6)
        print("  test_subtract_numbers executed.")

if __name__ == '__main__':
    unittest.main()