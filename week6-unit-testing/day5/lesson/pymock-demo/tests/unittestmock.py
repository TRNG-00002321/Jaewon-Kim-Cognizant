import unittest
from unittest.mock import Mock

def greet(func):
    return "Hello, " + func()

class TestMockBasic(unittest.TestCase):
    def test_mock_return_value_and_assert_call(self):
        fake_func = Mock(return_value="World")
        result = greet(fake_func)

        # behavior check
        self.assertEqual(result, "Hello, World")

        # validation: was called once
        fake_func.assert_called_once()
        # validation: called with no arguments
        fake_func.assert_called_once_with()
        
    def test_mock_example():
        #Create mock
        weather_api = Mock()
        #Configure mock return value
        weather_api.get_temperature.return_value=25
        
        print(weather_api.get_temperature)
        print(weather_api.get_temperature())
        #Use a mock
        temp =weather_api.get_temperature('Plano')
        print(temp)
        #Verify/asserting the method was called
        weather_api.get_temperature.assert_called_with('Plano')

if __name__ == "__main__":
    unittest.main()