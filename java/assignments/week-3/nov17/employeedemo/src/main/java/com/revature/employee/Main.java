package com.revature.employee;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //add menu interface with scanner, use do while loop
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Employee Manager:");
        while(true){
          System.out.println("1.)Create new Contractual Employee");
          System.out.println("2.)Create new Salaried Employee");
          System.out.println("4.)View all Employees");
          System.out.println("3.)Exit");

          int input = scanner.nextInt();
          switch(input){
            case 1->{
                System.out.println("Enter Days worked: ");
                int days = Integer.parseInt(scanner.next());

                System.out.println("Enter Daily Pay-rate: ");
                double payRate = Double.parseDouble(scanner.next());

                System.out.println("Enter benifits(sperated by spaces): ");
                String[] benifits = scanner.next().split(" ");

            }
            case 2->{
                
            }
            case 3->{
                
            }
            default ->{
                System.out.println("Please enter options: {1,2,3}!");
            }
          }

        }

        Employee se = new SalaryEmployee(5, new String[]{"Premium Health Care"}, 100.00);
        Employee ce = new ContractEmployee(5, 50);
        System.out.println(se+" Pay: "+se.calculateSalary());
        System.out.println(ce+" Pay: "+ce.calculateSalary());
    }
}