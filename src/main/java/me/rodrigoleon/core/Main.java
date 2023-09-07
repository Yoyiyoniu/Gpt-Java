package me.rodrigoleon.core;


import me.rodrigoleon.gpt.Gpt;

public class Main{

    public static void main(String[] args) {
        Gpt.setApiKey("#");
        String prompt = Gpt.Prompt("Hola como estas?");

        System.out.println(prompt);
    }


}