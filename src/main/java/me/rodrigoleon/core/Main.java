package me.rodrigoleon.core;


import me.rodrigoleon.gpt.Gpt;

public class Main extends Gpt {

    public static void main(String[] args) {
    Gpt.setApiKey("#");
    Gpt.DEBUGMODE(false);


    Gpt.roll("batman", "en donde vives?");

    }


}