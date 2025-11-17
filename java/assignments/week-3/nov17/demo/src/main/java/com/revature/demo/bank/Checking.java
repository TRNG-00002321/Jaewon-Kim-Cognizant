package com.revature.demo.bank;

import com.revature.demo.exceptions.OverDrawExpection;

public class Checking extends Account {
    public Checking(){

    }
    public Checking(String id, String name, double amount){
        super(id, name, amount);
    }

    @Override
    public double withdraw(double amount)  throws ArithmeticException, OverDrawExpection {
        double surcharge = (amount*0.01)/100;
        if(amount < 0) throw new ArithmeticException();
        if((super.getAmount() - amount - surcharge) < 5000) throw new OverDrawExpection();
        super.setAmount(super.getAmount() - amount - surcharge);
        return super.getAmount();
    }

    @Override
    public String toString(){
        return "Checking "+super.toString();
    }
}
