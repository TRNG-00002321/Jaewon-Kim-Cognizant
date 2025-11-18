package com.collections;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        Set<String> names = new HashSet<String>();
        names.add("Adam");
        names.add("Cdam");
        names.add("Bdam");
        names.add("Adam");
        names.add("Sdam");

        System.out.println(names.size());
        for(String name: names){
            System.out.println(name);
        }

        names.remove("Adam");
        for(String name: names){
            System.out.println(name);
        }

        names.clear();
        for(String name: names){
            System.out.println(name);
        }
    }
}
