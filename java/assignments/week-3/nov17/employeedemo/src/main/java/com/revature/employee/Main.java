package com.revature.employee;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //add menu interface with scanner, use do while loop
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Employee Manager:");
        loop: while(true){
          System.out.println("1.)Create new Salaried Employee");
          System.out.println("2.)Create new Contractual Employee");
          System.out.println("3.)View all Employees");
          System.out.println("4.)Exit");

          int input = scanner.nextInt();
          switch(input){
            case 1->{
                System.out.println("Enter Days worked: ");
                int days = Integer.parseInt(scanner.next());

                System.out.println("Enter Daily Pay-rate: ");
                double payRate = Double.parseDouble(scanner.next());

                System.out.println("Enter benifits(sperated by comma): ");
                String[] benifits = scanner.next().split(",");

                Employee se = new SalaryEmployee(days, benifits, payRate);
                employees.add(se);
                System.out.println(se+" Pay: "+se.calculateSalary());
            }
          case 2->{
              System.out.println("Enter hours worked: ");
              double hours = Double.parseDouble(scanner.next());

              System.out.println("Enter Hourly Pay-rate: ");
              double payRate = Double.parseDouble(scanner.next());

              Employee ce = new ContractEmployee(hours, payRate);
              employees.add(ce);
              System.out.println(ce+" Pay: "+ce.calculateSalary());   
            }
            case 3->{
              for(Employee item: employees){
                System.out.println(item);
              }
            }
            case 4->{
                break loop;
            }
            default ->{
                System.out.println("Please enter options: {1,2,3}!");
            }
          }

        }
    }
}