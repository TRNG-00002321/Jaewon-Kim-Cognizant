package Calculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.testing.Calculator;

public class CalculatorUnitTestAdd {
    @Test
    @DisplayName("Testing Calculator.add(): Positive")
    public void testAddPositive(){
        //Arrange
        Calculator calc = new Calculator();
        //Act
        int num1 = 10;
        int num2 = 12;
        int expected = 22;
        //Assert
        int actual = calc.add(num1, num2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testing Calculator.add(): Negative")
    public void testAddNegative(){
        //Arrange
        Calculator calc = new Calculator();
        //Act
        int num1 = -10;
        int num2 = 12;
        int expected = 22;
        //Assert
        int actual = calc.add(num1, num2);
        Assertions.assertNotEquals(expected, actual);
    }

    @BeforeEach
    public void setUp(){
        System.out.println("Before Each");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("After Each");
    }

    @BeforeAll
    public static void setUpClass(){
        System.out.println("Before all");
    }

    @AfterAll
    public static void tearDownClass(){
        System.out.println("After all");
    }
}
