package com.company;

import main_structure.Titolo;

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
                Thread.sleep(1000);
                titolo.updateValue();
            }catch(Exception e){
                System.out.println("Qualcosa è andato storto nel Clock");
            }
        }
    }
}
