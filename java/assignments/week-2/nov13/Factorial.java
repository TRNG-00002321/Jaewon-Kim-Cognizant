import java.util.Scanner;

public class Factorial {
    public static int itFactorial(int num){
        int total = 1;
        for(int i = 1; i <= num; i++){  // separate counter variable
            total *= i;  // multiply total by the counter
        }
        return total;
    }

    public static int reFactorial(int num){
        if(num == 0) return 1;
        return num*reFactorial(num-1);
    }

    public static void main(String[] args) {
        System.out.print("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.next());
        System.out.println(itFactorial(input));
        System.out.println(reFactorial(input));
    }
}
