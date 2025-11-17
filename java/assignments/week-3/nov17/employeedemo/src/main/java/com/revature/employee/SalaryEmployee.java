package com.revature.employee;

import java.util.Arrays;

public class SalaryEmployee implements Employee{
    private int daysWorked;
    private String[] benifits;
    private double dailyPayRate;

    public SalaryEmployee(int daysWorked, String[] benifits, double dailyPayRate) {
        this.daysWorked = daysWorked;
        this.benifits = benifits;
        this.dailyPayRate = dailyPayRate;
    }

    @Override
    public String toString() {
        return "ContractEmployee [daysWorked=" + daysWorked + ", benifits=" + Arrays.toString(benifits)
                + ", dailyPayRate=" + dailyPayRate + "]";
    }

    public SalaryEmployee() {
        dailyPayRate = 100;
        daysWorked = 0;
        benifits = new String[]{"Basic Health Package"};
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public String[] getBenifits() {
        return benifits;
    }

    public void setBenifits(String[] benifits) {
        this.benifits = benifits;
    }

    public double getDailyPayRate() {
        return dailyPayRate;
    }

    public void setDailyPayRate(double dailyPayRate) {
        this.dailyPayRate = dailyPayRate;
    }

    @Override
    public double calculateSalary() {
        return daysWorked*dailyPayRate;
    }

}
