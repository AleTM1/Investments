package com.company;

import main_structure.Titolo;

import java.util.concurrent.TimeUnit;

class Clock {
    private int maxTick;
    private static Clock self = null;

    private Clock(int mT){
        maxTick = mT;
    }
    static Clock getInstance(int mT){
        if(self != null){
            return self;
        }else{
            return new Clock(mT);
        }
    }
    void run(Titolo titolo){
        for(int i = 0; i < maxTick; i++){
            try {
                System.out.println("Tick assoluto: " + i);
                TimeUnit.SECONDS.sleep(1);
                titolo.updateValue();
            }catch(InterruptedException e){
                System.out.println("Qualcosa è andato storto nel Clock");
            }
        }
    }
}
