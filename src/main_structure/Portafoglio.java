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
        monitorRendimenti.extendVariationsArray();
        t.addObserver(monitorRendimenti);
        value += t.getValue();
        initialValue = value;
    }

    private Azione regenerateAzione(){
        System.out.println("Azione rigenerata");
        Azione azione = new Azione();
        azione.generateMaxDecPer();
        azione.generateMaxIncPer();
        azione.setValue(1000);
        azione.addObserver(monitorRendimenti);
        value = value + azione.getValue();
        return azione;
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
        System.out.println("Valore del portafoglio: " + value);

        _notify(this, variation);

        currentTick++;
        System.out.println("Current tick" + currentTick);

        if(currentTick == interval){
            System.out.println("Controllo rendimento");
            if(value < initialValue) {
                System.out.println("Perdita!!!");
                Titolo titoloPeggiore = monitorRendimenti.requestAnalisys();
                value = value - titoloPeggiore.getValue();
                int index = arrayTitoli.indexOf(titoloPeggiore);
                arrayTitoli.set(index, regenerateAzione());
                initialValue = value;
            }
            System.out.println("Nuovo valore" + value);
            monitorRendimenti.resetVariations();
            currentTick = 0;
        }

    }
}
