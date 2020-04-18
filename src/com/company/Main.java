package com.company;

public class Main {

    public static void main(String[] args) {

        Patrimonio patrimonio = new Patrimonio(10000, 3.2);
        System.out.println("Amount "+ patrimonio.getTotalAmount());
        patrimonio.startAutomaticMenagement(10);

    }
}
