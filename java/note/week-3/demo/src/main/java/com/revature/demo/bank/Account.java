package com.revature.demo.bank;

public abstract  class Account {
    private String id;
    private String name;
    private double amount;
    
    public Account(String id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    public double deposit(double amount){
        this.amount+=amount;
        return this.amount;
    }

    public abstract double withdraw(double amount);
}
