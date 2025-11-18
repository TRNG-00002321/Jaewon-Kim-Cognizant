package com.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add(1);
        list.add("poggers");
        list.add(10.3);
        list.add(1, "George");

        LinkedList linkedList = new LinkedList<>(list);

        System.out.println("For loop:");
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));    
        }
        System.out.println("Adding All: ");
        list.addAll(linkedList);
        System.out.println("Enhanced For loop:");
        for(Object o: list){
            System.out.println(o);
        }

        System.out.println("Remove: ");
        list.remove(1);
        list.remove("poggers");
        System.out.println("Iterator loop:");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list.contains("poggers"));
        

        System.out.println("For loop:");
        for(int i = 0; i<linkedList.size(); i++){
            System.out.println(list.get(i));    
        }
        System.out.println("Adding All: ");
        linkedList.addAll(list);
        System.out.println("Enhanced For loop:");
        for(Object o: linkedList){
            System.out.println(o);
        }

        System.out.println("Remove: ");
        linkedList.remove(1);
        linkedList.remove("poggers");
        System.out.println("Iterator loop:");
        Iterator linkedIterator = linkedList.iterator();
        while (linkedIterator.hasNext()) {
            System.out.println(linkedIterator.next());
        }

        System.out.println("Remove: ");
        linkedList.removeAll(list);
        System.out.println("Iterator loop:");
        while (linkedIterator.hasNext()) {
            System.out.println(linkedIterator.next());
        }

        System.out.println("Remove: ");
        linkedList.clear();
        System.out.println("Iterator loop:");
        while (linkedIterator.hasNext()) {
            System.out.println(linkedIterator.next());
        }

        System.out.println(linkedList.isEmpty());
        System.out.println(list.isEmpty());

        System.out.println(linkedList.contains("poggers"));
    }
}