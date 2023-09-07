package me.rodrigoleon.core;


import me.rodrigoleon.gpt.Gpt;

public class Main{

    public static void main(String[] args) {
        Gpt.setApiKey("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");
        String prompt = Gpt.Prompt("Hola como estas?");

        System.out.println(prompt);
    }


}