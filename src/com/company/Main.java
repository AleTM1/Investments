package com.company;

public class Main {

    public static void main(String[] args) {

        double initialAmount = 10000;
        double profileRisk = 4;
        int duration = 100;
        int maxPortafogli = 10;

        Patrimonio patrimonio = new Patrimonio(initialAmount, profileRisk);
        System.out.println("Amount "+ patrimonio.getTotalAmount());
        patrimonio.startAutomaticMenagement(duration, maxPortafogli);

    }
}
