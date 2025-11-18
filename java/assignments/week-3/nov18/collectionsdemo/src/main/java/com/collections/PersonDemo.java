package com.collections;

import java.util.ArrayList;

public class PersonDemo {
    int age;
    int id;
    String name;

    public PersonDemo(int age, int id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }
    
    public static void main(String[] args) {
        ArrayList<PersonDemo> people = new ArrayList<>();
        people.add(new PersonDemo(12, 0, "Jeb"));
        people.add(new PersonDemo(13, 2, "Jebby"));
        people.add(new PersonDemo(47, 3, "Jeck"));
        people.add(new PersonDemo(1, 4, "Jemanthal"));
        people.add(new PersonDemo(67, 5, "Jebuary"));

        for(PersonDemo p: people) System.out.println(p.name+" "+p.age+" "+p.id);
    }
}
