package com.company;

import main_structure.Portafoglio;

public class Main {

    public static void main(String[] args) {

        Clock clock = Clock.getInstance(10);
        Portafoglio portafoglio = new Portafoglio();
        portafoglio.setRoot();

        for(int i=0; i<5; i++) {
            portafoglio.addTitolo(portafoglio.generateAzione());
            System.out.println("Azione creata " + i);
        }


        Portafoglio p2 = new Portafoglio();

        for(int i=0; i<5; i++) {
            p2.addTitolo(portafoglio.generateAzione());
            System.out.println("Azione creata " + i);
        }

        portafoglio.addTitolo(p2);

        clock.run(portafoglio);

    }
}
