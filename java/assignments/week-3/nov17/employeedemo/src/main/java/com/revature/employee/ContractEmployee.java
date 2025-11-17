package com.revature.employee;

public class ContractEmployee implements Employee {
    private double hoursWorked;
    private double hourlyPayRate;

    
    public ContractEmployee(double hoursWorked, double hourlyPayRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyPayRate = hourlyPayRate;
    }

    public ContractEmployee() {
        this.hoursWorked = 0;
        this.hourlyPayRate = 8.50;
    }

    @Override
    public String toString() {
        return "SalaryEmployee [hoursWorked=" + hoursWorked + ", hourlyPayRate=" + hourlyPayRate + "]";
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    @Override
    public double calculateSalary() {
        return hourlyPayRate*hoursWorked;
    }
}
