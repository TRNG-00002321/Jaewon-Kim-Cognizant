package com.example.filestreamdemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStreamDemo {
    public static void main(String[] args) throws IOException {
        //FileReader inputStream = null;
        
        try (   
            FileReader inputStream = new FileReader("/home/user/rev/training/java/assignments/week-4/filedemo/example.txt");
            FileWriter outputStream = new FileWriter("output1.txt");
        ){
            int c;
            while((c=inputStream.read()) != -1){
                System.out.println((char) c);
                outputStream.write(c);
            }
        } catch (Exception e) {
        }
    }
}
