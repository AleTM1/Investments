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

    double startAutomaticMenagement(int duration, int intervall ,int maxPortafogli){
        Clock clock = Clock.getInstance(duration);
        Portafoglio portafoglio = new Portafoglio(risk, intervall, maxPortafogli);
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        clock.run(portafoglio);
        totalAmount = portafoglio.getValue();
        Printer printer = new Printer();
        printer.printStructure(portafoglio);
        return totalAmount;
    }
}
