import java.util.Scanner;
/*
11/12 Java Assignments Set 1:
1) Using a switch case statement, receive input 1-7 and return corresponding day of the week
2) Using if else logic statements, return the greater of 3 numeric inputs
3) Using while loops, display the mutliplication table up to 10 of an input numeric
4) Using for loops, print a * staircase such that the nth line has n stars. You can also do a backwards staircase afterwards for symmetry.
 */
public class Control {
    public static void dayOfTheWeek(Scanner scanner){
        System.out.println("Day of the Week: ");
        System.out.println("Enter number: ");
        int day = Integer.parseInt(scanner.next());
        switch (day) {
            case 1 ->{
                System.out.println("Monday");
            }
            case 2 ->{
                System.out.println("Tuesday");
            }
            case 3 ->{
                System.out.println("Wednsday");
            }
            case 4 ->{
                System.out.println("Thrusday");
            }
            case 5 ->{
                System.out.println("Friday");
            }
            case 6 ->{
                System.out.println("Saturday");
            }
            case 7 ->{
                System.out.println("Sunday");
            }
        }
    }


    public static void myStaircase(Scanner scanner){
        System.out.println("My Staircase: ");
        System.out.println("Enter number: ");
        int num = Integer.parseInt(scanner.next());
        for(int i = 0; i<=num; i++){
            for(int j = 0; j<=i; j++){
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }


    public static void greatestValue(Scanner scanner){
        System.out.println("Greatest Value: ");
        System.out.println("Enter number: ");
        int num1 = Integer.parseInt(scanner.next());
        System.out.println("Enter number: ");
        int num2 = Integer.parseInt(scanner.next());
        System.out.println("Enter number: ");
        int num3 = Integer.parseInt(scanner.next());
        if(num1>num2){
            if(num1>num3){
                System.out.println(num1);
            } else {
                System.out.println(num3);
            }
        } else{
            if(num2>num3){
                System.out.println(num2);
            } else {
                System.out.println(num3);
            }
        }


    }


    public static void timesTable(Scanner scanner){
        System.out.println("My Times Table: ");
        System.out.println("Enter number: ");
        int num = Integer.parseInt(scanner.next());
        int times = 1;
        while(times <= 10){
            System.out.println(num*times);
            times++;
        }
   }


    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            Control.dayOfTheWeek(scanner);
            Control.greatestValue(scanner);
            Control.myStaircase(scanner);
            Control.timesTable(scanner);
        }
    }
}


