package com.assignment.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "Fimbo", 10000));
        employees.add(new Employee(2, "Wimbo", 12000));
        employees.add(new Employee(4, "Timbo", 40000));
        employees.add(new Employee(5, "Rimbo", 70000));
        employees.add(new Employee(1, "Gimbo", 90000));
        for(Employee e: employees) System.out.println(e);

        List<String> names = employees.stream()
            .map(e -> e.getName().toUpperCase())
            .collect(Collectors.toList());
        for(String name: names) System.out.println(name);

        List<Employee> highEarners = employees.stream()
            .filter(e -> e.getSalary() > 20000)
            .collect(Collectors.toList());
        for(Employee e: highEarners) System.out.println(e);
    }
}
