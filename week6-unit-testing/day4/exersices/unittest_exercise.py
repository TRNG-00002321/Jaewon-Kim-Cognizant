import unittest
from math_operations import MathOperations


class TestMathOperations(unittest.TestCase):
    """Tests for the MathOperations class."""
    
    @classmethod
    def setUpClass(cls):
        """Run once before all tests in this class."""
        cls.prime_cache = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
        print("Prime cache initialized")
    
    def setUp(self):
        """Set up test fixtures before each test method."""
        self.math = MathOperations()
    
    def test_factorial_zero_returns_one(self):
        self.assertEqual(self.math.factorial(0), 1)

    def test_factorial_five_returns_120(self):
        self.assertEqual(self.math.factorial(5), 120)
        
    def test_fibonacci_zero_returnsZero(self):
        self.assertEqual(self.math.fibonacci(0), 0)
        
    def test_fibonacci_ten_returns55(self):
        self.assertEqual(self.math.fibonacci(10), 55)
        
    def test_isPrime_2_returnsTrue(self):
        self.assertEqual(self.math.is_prime(2), True)
        
    def test_isPrime_4_returnsFalse(self):
        self.assertEqual(self.math.is_prime(4), False)
        
    def test_gcd_48and18_returns6(self):
        self.assertEqual(self.math.gcd(48,18),6)
        
    def test_factorial_negative_raises_value_error(self):
        with self.assertRaises(ValueError):
            self.math.factorial(-1)

    def test_factorial_negative_has_correct_message(self):
        with self.assertRaises(ValueError) as context:
            self.math.factorial(-1)
        self.assertIn("negative", str(context.exception).lower())
    
    def tearDown(self):
        """Clean up after each test method."""
        pass  # No cleanup needed for this simple class
    
    def test_is_prime_returns_boolean(self):
        result = self.math.is_prime(7)
        self.assertIsInstance(result, bool)

    def test_prime_list_contains_expected(self):
        primes = self.math.get_primes_up_to(10)
        self.assertIn(7, primes)
        self.assertNotIn(4, primes)

    def test_factorial_large_number(self):
        result = self.math.factorial(10)
        self.assertGreater(result, 1000000)  # 10! = 3,628,800

    def test_gcd_returns_positive(self):
        result = self.math.gcd(-12, 18)
        self.assertGreaterEqual(result, 0)
    
    @classmethod
    def tearDownClass(cls):
        """Run once after all tests in this class."""
        print("Cleaning up class resources")


if __name__ == '__main__':
    unittest.main()