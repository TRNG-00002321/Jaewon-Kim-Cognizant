package com.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeDemo {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "Fimbo", 1));
        employees.add(new Employee(2, "Wimbo", 12));
        employees.add(new Employee(4, "Timbo", 40));
        employees.add(new Employee(5, "Rimbo", 70));
        employees.add(new Employee(1, "Gimbo", 90));
        System.out.println("No Sort");
        for(Employee e: employees) System.out.println(e);

        System.out.println("\nName Sort");
        Collections.sort(employees, Comparator.comparing(Employee::getName));
        for(Employee e: employees) System.out.println(e);

        System.out.println("\nId Sort");
        Collections.sort(employees, Comparator.comparing(Employee::getId));
        for(Employee e: employees) System.out.println(e);

        System.out.println("\nSalary Sort");
        Collections.sort(employees, Comparator.comparing(Employee::getSalary));
        for(Employee e: employees) System.out.println(e);
    }
}
