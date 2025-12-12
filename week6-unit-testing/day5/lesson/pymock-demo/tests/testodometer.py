import unittest
from unittest.mock import Mock
from pymock_demo import odometer

class TestOdometer(unittest.TestCase):
    def test_alert_lessThan60_returnfalse(self):
        odometer.speed = Mock()
        odometer.speed.return_value = 59
        self.assertTrue(odometer.alert())

if __name__ == '__main__':
    unittest.main()