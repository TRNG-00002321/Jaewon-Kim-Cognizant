package com.revature.demo.bank;

import com.revature.demo.exceptions.OverDrawExpection;

public class Savings extends Account implements SimpleIntrest {

    public Savings(){

    }

    public Savings(String id, String name, double amount){
        super(id, name, amount);
    }

    @Override
    public double withdraw(double amount) throws ArithmeticException, OverDrawExpection {
        if(amount < 0) throw new ArithmeticException();
        if((super.getAmount() - amount) < 5000) throw new OverDrawExpection();
        super.setAmount(super.getAmount() - amount);
        return super.getAmount();
    }

    @Override
    public String toString(){
        return "Savings "+super.toString();
    }

    @Override
    public double calculateIntrest(double percent) {
        return super.getAmount()*percent;
    }
}
