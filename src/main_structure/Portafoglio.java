package main_structure;

import java.util.ArrayList;

public class Portafoglio extends Titolo {
    private static int intervall;
    private final int brenchFactor = 2;
    private ArrayList<Titolo> arrayTitoli;
    private MonitorRendimenti monitorRendimenti;
    private int currentTick = 0;
    private AzioneBuilder builder;
    private boolean root;
    private double initialValue = value;
    private static int currentId = 0;
    private static int maxPortafogli;
    private int id;

    public Portafoglio(double risk, int interv, int maxPort){
        intervall = interv;
        maxPortafogli = maxPort;
        id = 0;
        root = true;
        arrayTitoli = new ArrayList<>();
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
        builder = new AzioneBuilder(risk);
    }

    private Portafoglio(AzioneBuilder b){
        id = ++currentId;
        root = false;
        arrayTitoli = new ArrayList<>();
        monitorRendimenti = new MonitorRendimenti(arrayTitoli);
        builder = b;
        System.out.println("Creato portafoglio: " + id);
    }

    public ArrayList<Titolo> getArrayTitoli() {
        return arrayTitoli;
    }

    public int getId() {
        return id;
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

        if (!root)
            _notify(this);

        currentTick++;
        if(currentTick % intervall == 0){
            if(value < initialValue) {
                System.out.println("Portafoglio: " + id + " in perdita");
                lossAnalisys();
            }else{
                System.out.println("Portafoglio: " + id + " in guadagno");
                if(currentId < maxPortafogli && arrayTitoli.size() == brenchFactor)
                    winUpgrade();
            }
            initialValue = value;
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
            Azione azione = generateAzione(titoloPeggiore.getValue());
            azione.addObserver(monitorRendimenti);
            arrayTitoli.set(index, azione);
            monitorRendimenti.resetVariation(index);
        }else{
            Portafoglio p = (Portafoglio) titoloPeggiore;
            p.lossAnalisys();
        }
    }

    private void winUpgrade(){
        // Si creano due nuovi portafogli inizializzati con 2 azioni ciascuno
        double newAzioniValue = this.getValue() / (Math.pow(brenchFactor, 3));
        Portafoglio p;
        this.setValue(this.getValue()/brenchFactor);
        for(int i=0; i<brenchFactor; i++){
            p = new Portafoglio(builder);
            for(int k=0; k<brenchFactor; k++) {
                p.addTitolo(p.generateAzione(newAzioniValue));
            }
            this.addTitolo(p);
        }
        // I valori delle azioni preesistenti vengono adeguati
        for(int i = 0; i<brenchFactor; i++){
            arrayTitoli.get(i).setValue(arrayTitoli.get(i).getValue() / brenchFactor);
        }
    }
}
