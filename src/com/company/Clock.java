package com.company;

import main_structure.Titolo;

final public class Clock {
    private final int maxTick;
    private static Clock self = null;

    private Clock(int mT){
        maxTick = mT;
    }
    public static Clock getInstance(int mT){
        if(self != null){
            return self;
        }else{
            self = new Clock(mT);
            return self;
        }
    }
    final void run(Titolo titolo) {
        try {
            for (int i = 0; i < maxTick; i++) {
                Thread.sleep(100);
                titolo.updateValue();
                System.out.println("Ammontare corrente: " + titolo.getValue() + " al tick " + i);
            }
        }catch(InterruptedException e){
                System.out.println("Qualcosa è andato storto nel Clock");
        }
    }
}


