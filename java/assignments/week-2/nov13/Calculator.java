import java.util.Scanner;

public class Calculator {
    class MyCalc{
        //Takes two ints and a char for operator to complete calcs
        public static int calc(int num1, int num2, char operation){
            int result = 0;
            switch(operation){
                case '*' ->{
                    result=num1*num2;
                }
                case '/' ->{
                    result=num1/num2;
                }
                case '+' ->{
                    result=num1+num2;
                }
                case '-' ->{
                    result=num1-num2;   
                }
            }
            return result;
        }
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter num: ");
        int num1 = Integer.parseInt(scanner.next());

        System.out.print("Enter num: ");
        int num2 = Integer.parseInt(scanner.next());

        System.out.print("Enter operation: ");
        char operation = scanner.next().charAt(0);
        scanner.close();

        System.out.println("Result: "+ MyCalc.calc(num1, num2, operation));

        System.out.println("Result (2,2,*): "+ MyCalc.calc(2, 2, '*'));
        System.out.println("Result (2,2,/): "+ MyCalc.calc(2, 2, '/'));
        System.out.println("Result (2,2,+): "+ MyCalc.calc(2, 2, '+'));
        System.out.println("Result (2,2,-): "+ MyCalc.calc(2, 2, '-'));
    }
}
