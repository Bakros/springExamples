package com.example.JavaExamples;

import java.sql.SQLOutput;

public class LambdaExample {
    public static void main(String[] args) {

        //Defining the Runnable, then the thread, then execute the thread.
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from Thread 1!");
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();

        //Define an anonymous Runnable that is used as a parameter for the definition of a Thread.
        //Then execute the Thread.
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from Thread 2");
            }
        });
        thread2.start();


        //Define an anonymous Thread that receives an anonymous Runnable Object (using Lambada) and execute that
        //anonymous Thread.
        new Thread(() -> System.out.println("Hello from Thread 3!")).start();

    }
}
