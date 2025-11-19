package com.assignment.lambda;

public class LambdaAssignment {
    public static void main(String[] args) {
        MyHelloLambda lambda = ()->{System.out.println("Hello!");};
        lambda.print();

        OtherHelloLambda lambda1 = (s) ->("Hello, "+s.toUpperCase());
        System.out.println(lambda1.cat("lowercase"));

        LastLambda lambda2 = (s,s1)->("Hello "+s+" "+s1);
        System.out.println(lambda2.cat("First", "Last"));
    }
}
