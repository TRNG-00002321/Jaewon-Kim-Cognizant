package com.example.optional;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
                String[] words = new String[10];
        words[6] = "ASDF";
        Optional<String> word= Optional.ofNullable(words[5]);
        Optional<String> word1= Optional.ofNullable(words[6]);
        System.out.println(word);
        System.out.println(word1);

        if(word.isPresent()){
            String myWord = word.get().toLowerCase();
            System.out.println(myWord);
        } else {
            System.out.println("Word is null");
        }

        if(word1.isPresent()){
            String myWord = word1.get().toLowerCase();
            System.out.println(myWord);
        } else {
            System.out.println("Word is null");
        }
    }
}
