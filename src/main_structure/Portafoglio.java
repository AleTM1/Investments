package main_structure;

import java.util.ArrayList;

public class Portafoglio extends Titolo {
    private static int interval = 3;
    private ArrayList<Titolo> arrayTitoli = new ArrayList<>();
    private MonitorRendimenti monitorRendimenti;
    private int currentTick = 0;
    private double initialValue = value;

    public Portafoglio(){
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
    }

    public void addTitolo(Titolo t){
        arrayTitoli.add(t);
        t.addObserver(monitorRendimenti);
    }

    private Azione regenerateAzione(){
        return new Azione();
    }

    @Override
    public void updateValue(){
        double actValue = 0;
        for(Titolo t : arrayTitoli){
            t.updateValue();
            actValue += t.getValue();
        }
        variation = actValue - value;
        value = actValue;
        _notify(this, variation);

        currentTick++;

        if(currentTick == interval){
            if(value < initialValue) {
                Titolo titoloPeggiore = monitorRendimenti.requestAnalisys();
                int index = arrayTitoli.indexOf(titoloPeggiore);
                arrayTitoli.set(index, regenerateAzione());
            }
            monitorRendimenti.resetVariations();
        }

    }
}
