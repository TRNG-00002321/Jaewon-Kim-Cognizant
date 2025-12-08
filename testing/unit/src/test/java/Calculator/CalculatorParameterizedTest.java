package Calculator;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.testing.Calculator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorParameterizedTest {
    private Calculator calculator = null;

    @BeforeEach
    public void setUp(){
        calculator=new Calculator();
    }

    @ParameterizedTest(name="{0} + {1} = {2}")
    @CsvSource({
        "1,2,3",
        "2,2,4"
    })
    @Order(2)
    public void testAdd(int a, int b, int expected){
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name="{0} + {1} = {2}")
    @CsvFileSource(resources="/testData.csv", numLinesToSkip=1)
    @Order(3)
    public void testAddCsvFile(int a, int b, int expected){
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name="{0} + {1} = {2}")
    @MethodSource("testDataGenerator")
    @Order(1)
    public void testAddMethod(int a, int b, int expected){
        assertEquals(expected, calculator.add(a, b));
    }

    public static Stream<Arguments> testDataGenerator(){
        return Stream.of(
            Arguments.of(1,2,3),
            Arguments.of(-2,2,0)
        );
    }

    @ParameterizedTest(name="{0} - {1} = {2}")
    @CsvSource({
        "1,2,-1",
        "2,2,0"
    })
    public void testSub(int a, int b, int expected){
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest(name="{0} * {1} = {2}")
    @CsvSource({
        "1,2,2",
        "2,2,4"
    })
    public void testMult(int a, int b, int expected){
        assertEquals(expected, calculator.multiply(a, b));
    }

    @ParameterizedTest(name="{0} / {1} = {2}")
    @CsvSource({
        "4,2,2",
        "2,2,1",
        "0,0,0"
    })
    public void testDiv(int a, int b, int expected){
        if(b == 0){
            IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, 
                () -> calculator.divide(a, b)
            );
            Assertions.assertEquals("Cannot divide by zero", exception.getMessage());
        } else{
            assertEquals(expected, calculator.divide(a, b));
        }
       
    }
}
