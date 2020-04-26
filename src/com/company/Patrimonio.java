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

    public double startAutoMenagement(int duration, int intervall, int maxPortafogli){
        try {
            verifyInputs(duration, intervall, maxPortafogli);
            Portafoglio portafoglio = new Portafoglio(risk, intervall, maxPortafogli);
            portafoglio.addTitolo(portafoglio.generateAzione(totalAmount / 2));
            portafoglio.addTitolo(portafoglio.generateAzione(totalAmount / 2));
            Clock clock = Clock.getInstance(duration);
            clock.run(portafoglio);
            totalAmount = portafoglio.getValue();
            Printer printer = new Printer();
            printer.printStructure(portafoglio);
        }catch (Exception e){
            System.out.println("Simulazione abortita. " + e.getMessage());
        }
        return totalAmount;
    }

    private void verifyInputs(int duration, int intervall, int maxPortafogli) throws Exception{
        if(duration < 2 || intervall < 1 || maxPortafogli < 1)
            throw new Exception("I valori minimi accettati sono: duration = 2, intervall = 1, maxPortafogli = 1.");
        if(intervall > duration)
            throw new Exception("L'intervallo non può superare la durata della simulazione.");
    }
}
