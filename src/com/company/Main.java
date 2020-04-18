package com.company;

import main_structure.Azione;

public class Main {

    public static void main(String[] args) {

        Clock clock = Clock.getInstance(10);
        Azione azione = new Azione();

        azione.generateMaxDecPer();
        azione.generateMaxIncPer();
        azione.setValue(1000);

        clock.run(azione);

    }
}
