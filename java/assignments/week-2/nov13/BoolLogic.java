import java.util.Scanner;

public class BoolLogic {
    public static int greatest(int num1, int num2, int num3){
        if(num1 > num3 || num2 > num3){
            if(num1>num2){
                return num1;
            } else {
                return num2;
            }
        } else {
            return num3;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter num: ");
        int num1 = Integer.parseInt(scanner.next());

        System.out.print("Enter num: ");
        int num2 = Integer.parseInt(scanner.next());

        System.out.print("Enter num: ");
        int num3 = Integer.parseInt(scanner.next());

        System.out.println("Greatest num: "+greatest(num1, num2, num3));

        System.out.print("Enter a: ");
        boolean a = Boolean.parseBoolean(scanner.next());

        System.out.print("Enter b: ");
        boolean b = Boolean.parseBoolean(scanner.next());

        System.out.println("When a="+a+", result !a: "+!a);
        System.out.println("When a="+a+" b="+b+", result a|b: "+(a|b));
        System.out.println("When a="+a+" b="+b+", result(!a&b)|(!b&a): "+((!a & b) | (!b & a)));
    }
}
