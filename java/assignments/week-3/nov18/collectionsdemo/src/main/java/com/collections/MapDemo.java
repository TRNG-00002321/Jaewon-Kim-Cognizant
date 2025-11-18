package com.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Double> people = new HashMap<>();
        
        people.put("Alex", 100000.0);
        people.put("Rebecca", 102000.0);
        people.put("Peter", 1430000.0);

        System.out.println(people.get("Peter"));

        Set<String> key = people.keySet();
        for(String name: key){
            System.out.println(name);
        }

        people.forEach((myKey, myValue) ->{
            System.out.println(myKey+":"+myValue);
        });
    }
}
