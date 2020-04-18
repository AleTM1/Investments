package com.company;

import main_structure.Azione;
import main_structure.MonitorRendimenti;
import main_structure.Portafoglio;
import main_structure.Titolo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Clock clock = Clock.getInstance(10);
        Portafoglio portafoglio = new Portafoglio();
        ArrayList<Titolo> array = new ArrayList<>();
        array.add(portafoglio);
        MonitorRendimenti monitor = new MonitorRendimenti(array);
        monitor.extendVariationsArray();
        portafoglio.addObserver(monitor);


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
