package com.revature.demo.person;

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Person{name:"+this.name+", age:"+this.age+"}";
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        Person p = (Person) o;
        if(!this.name.equals(p.getName())) return false;
        if(this.age != p.getAge()) return false;
        return true;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this);
    }
}
