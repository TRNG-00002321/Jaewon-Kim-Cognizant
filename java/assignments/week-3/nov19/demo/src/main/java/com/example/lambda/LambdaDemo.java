package com.example.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
        Calculator calculator = (num1 , num2)->(num1+num2);
        System.out.println(calculator.operation(1, 2));
        printResult(1, 2, calculator);
        printResult(1, 2, (num1, num2)->(num1*num2));
    }

    public static void printResult(int a, int b, Calculator lambda){
        int result = lambda.operation(a, b);
        System.out.println(result);
    }
}
