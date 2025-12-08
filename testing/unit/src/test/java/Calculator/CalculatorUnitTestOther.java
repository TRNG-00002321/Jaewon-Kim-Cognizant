package Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.testing.Calculator;

public class CalculatorUnitTestOther {
    @Test
    public void testSubtract(){
        Calculator calculator = new Calculator();
        int num1 = 1;
        int num2 = 6;
        int expected = -5;
        int actual = calculator.subtract(num1, num2);
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testMultiply(){
        Calculator calculator = new Calculator();
        int num1 = 1;
        int num2 = 6;
        int expected = 6;
        int actual = calculator.multiply(num1, num2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDivide(){
        Calculator calculator = new Calculator();
        int num1 = 1;
        int num2 = 6;
        int expected = 1/6;
        int actual = calculator.divide(num1, num2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDivideIllegalArguments() {
        Calculator calculator = new Calculator();
        int num1 = 1;
        int num2 = 0;
        
        IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class, 
            () -> calculator.divide(num1, num2)
        );
        
        Assertions.assertEquals("Cannot divide by zero", exception.getMessage());
    }
}
