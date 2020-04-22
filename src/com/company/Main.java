package com.company;

public class Main {

    public static void main(String[] args) {

        double initialAmount = 10000;
        double profileRisk = 4;
        int duration = 200;
        int intervall = 10;
        int maxPortafogli = 30;

        Patrimonio patrimonio = new Patrimonio(initialAmount, profileRisk);
        System.out.println("Amount "+ patrimonio.getTotalAmount());
        patrimonio.startAutomaticMenagement(duration, intervall, maxPortafogli);

    }
}
