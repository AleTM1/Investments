package com.company;

import main_structure.Titolo;

public class Clock {
    private int maxTick;
    private static Clock self = null;

    private Clock(int mT){
        maxTick = mT;
    }
    public static Clock getInstance(int mT){
        if(self != null){
            return self;
        }else{
            return new Clock(mT);
        }
    }
    public void run(Titolo titolo){
        for(int i = 0; i < maxTick; i++){
            try {
                Thread.sleep(1000);
                titolo.updateValue();
            }catch(Exception e){}
        }
    }
}
