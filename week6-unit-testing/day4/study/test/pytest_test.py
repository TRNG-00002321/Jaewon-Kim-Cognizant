import sys
from src.calculator import Calculator
import pytest

@pytest.fixture
def resource_fixture():
    print("\nSetting up resource...")
    resource = "my_shared_resource"
    yield resource  # The value 'resource' is yielded to the test function
    print("Tearing down resource...")

def test_using_resource(resource_fixture):
    print(f"Test is using: {resource_fixture}")
    assert resource_fixture == "my_shared_resource"

def test_calculator():
    calculator = Calculator()
    result = calculator.add(1,2)
    assert result == 3
    
def test_iseven():
    calculator = Calculator()
    assert calculator.is_even(2)
    assert not calculator.is_even(3)
    
def test_divide_by_zero():
    calculator = Calculator()
    with pytest.raises(ZeroDivisionError):
        calculator.divide(3,0)
        
def test_conftest_user(sample_data):
    assert sample_data["name"] == "Alex"
    
@pytest.mark.parametrize("input1, input2, expected_output", [
    (1, 1, 2),
    (2, 2, 4),
    (3, 3, 6),
])
def test_add(input1, input2, expected_output):
    calculator = Calculator()
    assert calculator.add(input1, input2) ==  expected_output

@pytest.mark.skip(reason="This feature is under development")
def test_new_feature():
    assert False
    
@pytest.mark.skipif(sys.platform != "win32", reason="Requires Windows")
def test_windows_only_functionality():
   assert True
   
@pytest.mark.skipif(sys.platform != "win32", reason="Requires Windows")
def test_windows_only_functionality():
   assert True