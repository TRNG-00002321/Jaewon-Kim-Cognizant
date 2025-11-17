package com.revature.demo.bank;

/*
 * Maker interfaces have no methods and only vars/values
 * Functional/Single abstract method interface jas only one method and supports functional programming
 * ex:
        @FunctionalInterface
        interface MyFunctionalInterface {
            void doSomething(); // The single abstract method
            
            default void doAnotherThing() { // A default method
                System.out.println("Doing another thing.");
            }
        }

        public class Main {
            public static void main(String[] args) {
                // Using a lambda expression to implement MyFunctionalInterface
                MyFunctionalInterface myInstance = () -> System.out.println("Doing something!");
                myInstance.doSomething();
                myInstance.doAnotherThing();
            }
        }
 */
public interface SimpleIntrest {
    //double DEFAULT_PERCENT = 10;
    double calculateIntrest(double percent);
}
