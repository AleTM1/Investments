package com.company;

public class Main {

    public static void main(String[] args) {

        double userAmount = 10000;
        double profileRisk = 4;
        int duration = 200;
        int intervall = 10;
        int maxPortafogli = 30;

        Patrimonio patrimonio = new Patrimonio(userAmount, profileRisk);
        System.out.println("Amount "+ patrimonio.getTotalAmount());
        userAmount = patrimonio.startAutomaticMenagement(duration, intervall, maxPortafogli);
        System.out.println("Al termine della simulazione il nuovo patrimonio ammonta a: " + userAmount);
    }
}
