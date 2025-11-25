package com.example.serialdemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.Employee;

public class WriteEmpObj {
    public static void main(String[] args) {
        Employee employee = new Employee(101, "Andrew", "Plano", "QEAs");
        try(
            FileOutputStream fileOutputStream = new FileOutputStream("employee.dat");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream("employee.dat");
            ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
        ) {
            objectOutput.writeObject(employee);
            Employee copy = (Employee) objectInput.readObject();
            System.out.println(copy);
        } catch (Exception e) {
        }
    }
}
