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

    public Portafoglio(){
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
        builder = new AzioneBuilder(monitorRendimenti);
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

    public Azione generateAzione(){
        builder.setStartingValue(1000);
        builder.setRangePer(5);
        Azione azione = builder.getResult();
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

        if (!root) {
            _notify(this, variation);
        }

        currentTick++;
        System.out.println("Current tick" + currentTick);

        if(currentTick == interval){
            System.out.println("Controllo rendimento");
            if(value < initialValue) {
                lossAnalisys();
            }
            System.out.println("Nuovo valore" + value);
            monitorRendimenti.resetVariations(-1);
            currentTick = 0;
        }

    }

    private void lossAnalisys(){
        Titolo titoloPeggiore = monitorRendimenti.requestAnalisys();
        if (titoloPeggiore instanceof Azione) {
            System.out.println("Perdita azionaria!!!");
            value = value - titoloPeggiore.getValue();
            int index = arrayTitoli.indexOf(titoloPeggiore);
            arrayTitoli.set(index, generateAzione());
            monitorRendimenti.resetVariations(index);
            initialValue = value;
        }else{
            System.out.println("Perdita di portafoglio!!!");
            Portafoglio p = (Portafoglio) titoloPeggiore;
            p.lossAnalisys();
        }
    }
}
