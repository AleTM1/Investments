package main_structure;

import observer_classes.Observer;
import java.util.ArrayList;

public class MonitorRendimenti implements Observer {

    private ArrayList<Titolo> arrayTitoli;
    private ArrayList<Double> variations = new ArrayList<>();

    public MonitorRendimenti(ArrayList<Titolo> array){
        arrayTitoli = array;
    }

    public void extendVariationsArray(){
        variations.add(0.0);
    }

    void resetVariations(int n){
        if(n < 0) {
            for (Double v : variations) {
                v = 0.0;
            }
        }else{
            variations.set(n, 0.0);
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
        int i = 0;
        for(Double v : variations){
            System.out.println("Azione n: " + i + " varazione: " + v);
            min = min > v ? v : min;
            i++;
        }
        System.out.println("Min: " + min);
        int index = variations.indexOf(min);
        return arrayTitoli.get(index);
    }
}
