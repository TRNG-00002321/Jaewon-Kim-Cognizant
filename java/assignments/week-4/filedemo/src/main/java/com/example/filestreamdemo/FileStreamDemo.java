package com.example.filestreamdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamDemo {
    public static void main(String[] args) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        int check;
        try {
            fileInputStream = new FileInputStream("/home/user/rev/training/java/assignments/week-4/filedemo/example.txt");
            fileOutputStream = new FileOutputStream("output1.txt");
            while((check=fileInputStream.read())!= -1){
                System.out.write(check);
                fileOutputStream.write(check);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
             e.printStackTrace();
        }
    }
}
