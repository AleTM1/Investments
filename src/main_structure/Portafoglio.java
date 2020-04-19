package main_structure;

import java.util.ArrayList;
import java.util.Iterator;

public class Portafoglio extends Titolo {
    private final int interval = 3;
    private final int brenchFactor = 2;
    private ArrayList<Titolo> arrayTitoli;
    private MonitorRendimenti monitorRendimenti;
    private int currentTick = 0;
    private AzioneBuilder builder;
    private boolean root;
    private double initialValue = value;
    private static int currentId = 0;
    private int id;

    public Portafoglio(double risk){
        id = 0;
        root = true;
        arrayTitoli = new ArrayList<>();
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
        builder = new AzioneBuilder(monitorRendimenti);
        builder.setRangePer(risk);
    }

    private Portafoglio(AzioneBuilder b){
        id = currentId + 1;
        currentId++;
        root = false;
        arrayTitoli = new ArrayList<>();
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
        builder = b;
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
        Azione azione = builder.getResult();
        azione.addObserver(monitorRendimenti);
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

        if (!root)
            _notify(this);

        currentTick++;
        if(currentTick % interval == 0){
            System.out.println("Controllo rendimento di " + id);
            if(value < initialValue) {
                lossAnalisys();
            }else if (value > initialValue && arrayTitoli.size() == brenchFactor){
                winUpgrade();
            }
            if(root)
                resetAll();
        }
    }

    private void resetAll(){
        monitorRendimenti.resetAllVariations();
        for (Titolo t : arrayTitoli) {
            if (t instanceof Portafoglio) {
                ((Portafoglio)t).resetAll();
            }
        }
    }

    private void lossAnalisys(){
        Titolo titoloPeggiore = monitorRendimenti.requestAnalisys();
        if (titoloPeggiore instanceof Azione) {
            int index = arrayTitoli.indexOf(titoloPeggiore);
            arrayTitoli.set(index, generateAzione(titoloPeggiore.getValue()));
            monitorRendimenti.resetVariation(index);
            initialValue = value;
            System.out.println("Perdita azionaria!!! Portafoglio " + id + " azione " + index);
        }else{
            System.out.println("Perdita di portafoglio!!!");
            Portafoglio p = (Portafoglio) titoloPeggiore;
            p.lossAnalisys();
        }
    }

    private void winUpgrade(){
        //si creano due nuovi portafogli inizializzati con 2 azioni ciascuno
        this.setValue(this.getValue() / 2);
        Portafoglio p;
        for(int i=0; i<brenchFactor; i++){
            p = new Portafoglio(builder);
            for(int k=0; k<brenchFactor; k++) {
                p.addTitolo(p.generateAzione(this.getValue()/(Math.pow(brenchFactor, 2))));
            }
            this.addTitolo(p);
        }
        // Le azioni preesistenti vengono adeguate
        for(int i = 0; i<brenchFactor; i++){
            arrayTitoli.get(i).setValue(arrayTitoli.get(i).getValue() / 2);
        }
        System.out.println("WIN portafoglio: " + id);
    }
}
