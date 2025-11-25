package com.example;

import java.io.Serializable;

public class Employee implements Serializable {
    
    private int empId;
    private String name;
    private String address;
    private transient String jobTitle;

    public Employee(){

    }

    public Employee(int empId, String name, String address, String jobTitle) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.jobTitle = jobTitle;
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
