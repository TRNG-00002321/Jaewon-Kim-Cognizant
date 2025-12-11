# math_operations.py
class MathOperations:
    """Mathematical operations for testing practice."""
    
    def factorial(self, n: int) -> int:
        """Calculate factorial of n."""
        if n < 0:
            raise ValueError("Factorial not defined for negative numbers")
        if n <= 1:
            return 1
        return n * self.factorial(n - 1)
    
    def fibonacci(self, n: int) -> int:
        """Return the nth Fibonacci number (0-indexed)."""
        if n < 0:
            raise ValueError("Fibonacci not defined for negative indices")
        if n <= 1:
            return n
        a, b = 0, 1
        for _ in range(2, n + 1):
            a, b = b, a + b
        return b
    
    def is_prime(self, n: int) -> bool:
        """Check if n is a prime number."""
        if n < 2:
            return False
        if n == 2:
            return True
        if n % 2 == 0:
            return False
        for i in range(3, int(n ** 0.5) + 1, 2):
            if n % i == 0:
                return False
        return True
    
    def get_primes_up_to(self, n: int) -> list:
        """Return list of primes up to n."""
        return [x for x in range(2, n + 1) if self.is_prime(x)]
    
    def gcd(self, a: int, b: int) -> int:
        """Calculate greatest common divisor."""
        a, b = abs(a), abs(b)
        while b:
            a, b = b, a % b
        return a