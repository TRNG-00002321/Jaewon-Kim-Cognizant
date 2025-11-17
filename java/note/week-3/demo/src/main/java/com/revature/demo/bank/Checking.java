package com.revature.demo.bank;

public class Checking extends Account {
    public Checking(){

    }
    public Checking(String id, String name, double amount){
        super(id, name, amount);
    }

    @Override
    public double withdraw(double amount) {
        double surcharge = (amount*0.01)/100;
        super.setAmount(super.getAmount() - amount - surcharge);
        return super.getAmount();
    }

    @Override
    public String toString(){
        return "Checking "+super.toString();
    }
}
