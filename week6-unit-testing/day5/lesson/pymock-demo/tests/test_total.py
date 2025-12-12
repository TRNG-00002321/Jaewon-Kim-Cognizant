from functools import reduce
import unittest
from unittest.mock import Mock, patch
from pymock_demo import total

class TestTotal(unittest.TestCase):
    def test_alert_lessThan60_returnfalse(self):
        total.read = Mock()
        total.read.return_value = [12.99, 1.01, 10.0]
        self.assertEqual(24, total.calculate_total('anyfile'))
        
    def test_total_with_patch(self):
        with patch('test_total.total.read', return_value=[12.99, 1.01, 10.0]):
            self.assertEqual(24, total.calculate_total('anyfile'))
            
    @patch('test_total.total.read', return_value=[12.99, 1.01, 10.0])
    def test_patch_decorator(self, mock_read):
        self.assertEqual(24, total.calculate_total('anyfile'))
        
    @patch('test_total.total.read', return_value=[12.99, 1.01, 10.0])
    def test_side_effects(self, mock_read):
        with patch('test_total.total.sum', side_effect = reduce(lambda a, b: a * b)):
            self.assertNotEqual(24, total.calculate_total('anyfile'))

    @patch('test_total.total.read', return_value=[12.99, 1.01, 10.0])
    def test_side_effects(self, mock_read):
        def negative_check_side_effect(filename):
            values = total.read(filename)
            if any(v<0 for v in values):
                raise ValueError("Negative values not allowed")
        
        with patch('test_total.total.calculate_total', side_effect = negative_check_side_effect):
            self.assertNotEqual(24, total.calculate_total('anyfile'))    
    

if __name__ == '__main__':
    unittest.main()