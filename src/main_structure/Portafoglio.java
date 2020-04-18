package main_structure;

import java.util.ArrayList;

public class Portafoglio extends Titolo {
    private final int interval = 3;
    private ArrayList<Titolo> arrayTitoli = new ArrayList<>();
    private MonitorRendimenti monitorRendimenti;
    private int currentTick = 0;
    private AzioneBuilder builder;
    private boolean root = false;
    private double initialValue = value;

    public Portafoglio(double risk){
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
        builder = new AzioneBuilder(monitorRendimenti);
        builder.setRangePer(risk);
    }

    public void setRoot() {
        this.root = true;
    }

    public void addTitolo(Titolo t){
        arrayTitoli.add(t);
        monitorRendimenti.extendVariationsArray();
        t.addObserver(monitorRendimenti);
        value += t.getValue();
        initialValue = value;
    }

    public Azione generateAzione(double initValue){
        builder.setStartingValue(initValue);
        return builder.getResult();
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

        if (!root) {
            _notify(this, variation);
        }

        currentTick++;
        System.out.println("Current tick" + currentTick);

        if(currentTick % interval == 0){
            System.out.println("Controllo rendimento");
            if(value < initialValue) {
                lossAnalisys();
            }
            monitorRendimenti.resetAllVariations();
        }
    }

    private void lossAnalisys(){
        Titolo titoloPeggiore = monitorRendimenti.requestAnalisys();
        if (titoloPeggiore instanceof Azione) {
            System.out.println("Perdita azionaria!!!");
            int index = arrayTitoli.indexOf(titoloPeggiore);
            arrayTitoli.set(index, generateAzione(titoloPeggiore.getValue()));
            monitorRendimenti.resetVariation(index);
            initialValue = value;
        }else{
            System.out.println("Perdita di portafoglio!!!");
            Portafoglio p = (Portafoglio) titoloPeggiore;
            p.lossAnalisys();
        }
    }
}
