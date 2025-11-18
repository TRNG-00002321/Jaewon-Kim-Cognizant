package com.revature.demo;

import com.revature.demo.bank.Account;
import com.revature.demo.bank.Checking;
import com.revature.demo.bank.Savings;
import com.revature.demo.exceptions.NegativeValueException;
import com.revature.demo.exceptions.OverDrawExpection;

public class Main {
    public static void main(String[] args) throws NegativeValueException, ArithmeticException, OverDrawExpection {
        Account savings = new Savings("S001", "Jimbo", 10.0);
        Account checking = new Checking("C001", "John Jimbo", 167.39);
        System.out.println(savings);
        System.out.println(checking);

        savings.deposit(20);
        checking.deposit(20);
        System.out.println(savings);
        System.out.println(checking);

        savings.withdraw(30);
        checking.withdraw(30);
        System.out.println(savings);
        System.out.println(checking);
    }
}