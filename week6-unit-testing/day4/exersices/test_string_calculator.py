import pytest
from string_calculator import StringCalculator


class TestStringCalculator:
    """Tests for the StringCalculator class."""
    
    def setup_method(self):
        """Create a fresh calculator for each test."""
        self.calc = StringCalculator()
    
    def test_add_empty_string_returns_zero(self):
        assert self.calc.add("") == 0

    def test_add_single_number_returns_that_number(self):
        assert self.calc.add("1") == 1
        assert self.calc.add("42") == 42

    def test_add_newline_delimiter(self):
        # "1\n2,3" should equal 6
        assert self.calc.add("1\n2,3") == 6
    
    def test_add_custom_delimiter(self):
        # "//;\n1;2" uses ; as delimiter
        assert self.calc.add("//;\n1;2") == 3
    
    def test_add_ignores_numbers_over_1000(self):
        # Numbers > 1000 should be ignored
        assert self.calc.add("2,1001") == 2
        assert self.calc.add("1000,1001,2") == 1002  # 1000 + 0 + 2
        
    def test_add_negative_number_raises_exception(self):
        with pytest.raises(ValueError) as exc_info:
            self.calc.add("-1,2")
        
        assert "negatives not allowed" in str(exc_info.value)
        assert "-1" in str(exc_info.value)
        
    def test_add_multiple_negatives_shows_all_in_message(self):
        with pytest.raises(ValueError) as exc_info:
            self.calc.add("-1,-2,3,-4")
        
        error_message = str(exc_info.value)
        assert "-1" in error_message
        assert "-2" in error_message
        assert "-4" in error_message
        
    