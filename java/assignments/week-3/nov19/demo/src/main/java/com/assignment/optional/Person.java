package com.assignment.optional;

public class Person {
    private String name;
    private long phone;
    public String getName() {
        return name;
    }
    public Person(){}
    public Person(String name, long phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    private Address address;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");
        sb.append("name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
    
}
