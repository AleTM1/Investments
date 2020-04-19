package main_structure;

import observer_classes.Observer;
import java.util.ArrayList;

public class MonitorRendimenti implements Observer {

    private ArrayList<Titolo> arrayTitoli;
    private ArrayList<Double> variations = new ArrayList<>();

    MonitorRendimenti(ArrayList<Titolo> array){
        arrayTitoli = array;
    }

    void extendVariationsArray(){
        variations.add(0.0);
    }

    void resetVariation(int index){
        variations.set(index, 0.0);
    }

    void resetAllVariations(){
        for (Double v : variations) {
            v = 0.0;
        }
    }

    @Override
    public void update(Object o) {
        Titolo titolo = (Titolo) o;
        int index = arrayTitoli.indexOf(titolo);
        variations.set(index, variations.get(index) + titolo.getVariation());
    }

    Titolo requestAnalisys(){
        double min = variations.get(0);
        for(Double v : variations){
            min = min > v ? v : min;
        }
        int index = variations.indexOf(min);
        return arrayTitoli.get(index);
    }
}
