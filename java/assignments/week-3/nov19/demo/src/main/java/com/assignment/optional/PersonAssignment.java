package com.assignment.optional;

import java.util.Optional;

public class PersonAssignment {
    public static void main(String[] args) {
        Address address = new Address("Hayes Street", "Dallas", 73045);
        Person p1 = new Person("Person-1", 1234567890, address);
        Person p2 = new Person("Person-1", 1234567890, null);

        Optional<Address> checkp1Adress = Optional.ofNullable(p1.getAddress());
        Optional<Address> checkp2Adress = Optional.ofNullable(p2.getAddress());

        if(checkp1Adress.isPresent()){
            System.out.println("P1 Address is present: "+p1.toString());
        } else {
            System.out.println("P1 Address is not present");
        }

        if(checkp2Adress.isPresent()){
            System.out.println("P2 Address is present: "+p2.toString());
        } else {
            System.out.println("P2 Address is not present");
        }
    }
}
