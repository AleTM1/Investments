package com.company;

import main_structure.Portafoglio;

class Patrimonio {
    private double totalAmount;
    private double risk;

    Patrimonio(double initialAmount, double profileRisk){
        if(profileRisk <= 0){
            risk = 0.5;
        }else if(profileRisk > 10){
            risk = 10;
        }else{
            risk = profileRisk;
        }
        totalAmount = initialAmount;
    }

    double getTotalAmount() {
        return totalAmount;
    }

    void startAutomaticMenagement(int duration, int maxPortafogli){
        Clock clock = Clock.getInstance(duration);
        Portafoglio portafoglio = new Portafoglio(risk,(int)(duration / 10.0), maxPortafogli);
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        portafoglio.addTitolo(portafoglio.generateAzione(totalAmount/2));
        clock.run(portafoglio);
    }
}
