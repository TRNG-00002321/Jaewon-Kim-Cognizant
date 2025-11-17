package com.revature.demo.bank;

public class Savings extends Account {

    public Savings(){

    }

    public Savings(String id, String name, double amount){
        super(id, name, amount);
    }

    @Override
    public double withdraw(double amount) {
        super.setAmount(super.getAmount() - amount);
        return super.getAmount();
    }

    @Override
    public String toString(){
        return "Savings "+super.toString();
    }

}
