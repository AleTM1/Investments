package com.company;

import main_structure.Portafoglio;

public class Patrimonio {
    private double totalAmount;
    private double risk;

    public Patrimonio(double initialAmount, double profileRisk){
        if(profileRisk <= 0){
            risk = 0.5;
        }else if(profileRisk > 10){
            risk = 10;
        }else{
            risk = profileRisk;
        }
        totalAmount = initialAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void startAutomaticMenagement(int duration){
        Clock clock = Clock.getInstance(duration);
        Portafoglio portafoglio = new Portafoglio(risk);
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        clock.run(portafoglio);
    }
}
