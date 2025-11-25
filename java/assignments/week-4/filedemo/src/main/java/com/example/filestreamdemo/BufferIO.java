package com.example.filestreamdemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferIO {
    public static void main(String[] args) {
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/user/rev/training/java/assignments/week-4/filedemo/example.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/user/rev/training/java/output1.txt"));
        ){
            String s;
            while((s = bufferedReader.readLine()) != null){
                System.out.println(s);
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
        }
    }
}
