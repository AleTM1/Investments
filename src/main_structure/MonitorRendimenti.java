package main_structure;

import observer_classes.Observer;
import java.util.ArrayList;

public class MonitorRendimenti implements Observer {

    private ArrayList<Titolo> arrayTitoli;
    private ArrayList<Double> variations = new ArrayList<>();

    public MonitorRendimenti(ArrayList<Titolo> array){
        arrayTitoli = array;
    }

    public void resetVariations(){
        for(Double v : variations){
            v = 0.0;
        }
    }

    @Override
    public void update(Object o, double variation){
        Titolo titolo = (Titolo) o;
        if(arrayTitoli.contains(titolo)){
            int index = arrayTitoli.indexOf(titolo);
            variations.set(index, variations.get(index) + variation);
        }else{
            arrayTitoli.add(titolo);
            variations.add(variation);
        }
    }

    public Titolo requestAnalisys(){
        double min = variations.get(0);
        for(Double v : variations){
            min = min > v ? v : min;
        }
        int index = variations.indexOf(min);
        return arrayTitoli.get(index);
    }
}