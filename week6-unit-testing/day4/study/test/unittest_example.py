import sys
import unittest

class TestStringMethods(unittest.TestCase):
    def test_upper(self):
        self.assertEqual('foo'.upper(), 'FOO')
    
    @unittest.skipIf(sys.platform == 'win32', "Skipping on Windows")
    def test_linux_only_feature(self):
        self.assertTrue(True)
        
    # @unittest.skipUnless(hasattr(self, 'resource_available'), "Resource not available")
    # def test_requires_resource(self):
    #     self.assertTrue(True) # This test runs only if 'resource_available' exists
    
    # def test_dynamic_skip(self):
    #     if not some_condition_is_met():
    #         self.skipTest("Condition not met for this test")
    #     self.assertTrue(True)
        
    @unittest.expectedFailure
    def test_known_bug(self):
        self.assertEqual(1, 2, "This test is expected to fail due to a known bug")
    
if __name__ == '__main__':
    unittest.main()