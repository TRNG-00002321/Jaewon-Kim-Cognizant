package com.example.assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerialAssignment {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void uploadEmployees(List<Employee> employees, String filename){
        try {
            mapper.writeValue(new File(filename), employees);
            System.out.println("Employees saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> loadEmployees(String filename){
        try {
            return mapper.readValue(
                new File(filename),
                mapper.getTypeFactory().constructCollectionType(List.class, Employee.class)
            );
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Andrew", "Plano", "QEAs"));
        employees.add(new Employee(101, "Andrew", "Plano", "QEAs"));
        employees.add(new Employee(101, "Andrew", "Plano", "QEAs"));

        uploadEmployees(employees, "employees.json");

        List<Employee> loadedEmployees = loadEmployees("employees.json");
        System.out.println("Loaded employees:");
        loadedEmployees.forEach(System.out::println);
    }
}
