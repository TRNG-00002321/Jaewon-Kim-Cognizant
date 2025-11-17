package com.revature.demo.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionsDemo {
    public static void main(String[] args){
        int[] myArr = new int[5];
        try {
            //Runtime example!
            myArr[5] = 10;
            System.out.print(myArr[5]);

            //comiple time exectption!
            //here the compiler is catching the issue! file not found
            FileInputStream fis = new FileInputStream("myFile.txt");
        } catch(FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Always printed!");
            //fis.close();
        }
        System.out.println("Ending");


    }
}
