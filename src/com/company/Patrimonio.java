package com.company;

import main_structure.Portafoglio;

public class Patrimonio {
    private double totalAmount;
    private double risk;

    public Patrimonio(double initialAmount, double profileRisk){
        if(profileRisk <= 0.5){
            risk = 0.5;
        }else if(profileRisk > 10){
            risk = 10;
        }else{
            risk = profileRisk;
        }
        totalAmount = initialAmount > 100 ? initialAmount : 100;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    void startAutomaticMenagement(int duration, int maxPortafogli){
        Clock clock = Clock.getInstance(duration);
        Portafoglio portafoglio = new Portafoglio(risk,(int)(duration / 10.0), maxPortafogli);
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        clock.run(portafoglio);
        Printer printer = new Printer();
        printer.printStructure(portafoglio);
    }
}
