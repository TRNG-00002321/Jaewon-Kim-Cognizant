# test_calculator_data_driven.py
import pytest
from calculator import Calculator
from conftest import load_test_cases


# Load test cases at module level
addition_cases = load_test_cases("calculator_tests.json", "addition_tests")
division_cases = load_test_cases("calculator_tests.json", "division_tests")
division_by_zero_cases = load_test_cases("calculator_tests.json", "division_by_zero_tests")


class TestCalculatorDataDriven:
    
    @pytest.fixture(autouse=True)
    def setup(self):
        self.calc = Calculator()
    
    @pytest.mark.parametrize(
        "a, b, expected, description",
        [(c["a"], c["b"], c["expected"], c["description"]) for c in addition_cases],
        ids=[c["description"] for c in addition_cases]
    )
    def test_addition(self, a, b, expected, description):
        """Test addition with data from JSON."""
        result = self.calc.add(a, b)
        assert result == expected, f"Failed: {description}"
    
    @pytest.mark.parametrize(
        "a, b, expected, description",
        [(c["a"], c["b"], c["expected"], c["description"]) for c in division_cases],
        ids=[c["description"] for c in division_cases]
    )
    def test_division(self, a, b, expected, description):
        """Test division with data from JSON."""
        result = self.calc.divide(a, b)
        assert result == pytest.approx(expected), f"Failed: {description}"
    
    @pytest.mark.parametrize(
        "a, b, description",
        [(c["a"], c["b"], c["description"]) for c in division_by_zero_cases],
        ids=[c["description"] for c in division_by_zero_cases]
    )
    def test_division_by_zero(self, a, b, description):
        """Test that division by zero raises exception."""
        with pytest.raises(ZeroDivisionError):
            self.calc.divide(a, b)