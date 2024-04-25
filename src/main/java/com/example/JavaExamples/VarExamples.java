package com.example.JavaExamples;

import java.util.ArrayList;

public class VarExamples {
    public static void main(String[] args) {
        var lista = new ArrayList<String>();

        lista.add("Yo");
        lista.add("Soy");
        lista.add("Una");
        lista.add("Frase");
        lista.add("Completa");

        for(String string:lista){
            System.out.println(string);
        }

        var numero = Integer.valueOf(25);
        System.out.println("\nThe number is "+numero+". And it is a number? > "+(25+1));

        String numberInString= numero.toString();

        // The IDE recognize the variable number declared with the var key is an Integer.
        // So the next commented line throws an error because the variable expect and Interger and not a String.
        // numero = "Hello";

    }
}
