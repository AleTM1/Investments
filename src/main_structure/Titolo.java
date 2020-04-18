package main_structure;
import observer_classes.Subject;

abstract public class Titolo extends Subject {
    double value = 0;
    double variation = 0;

    public double getValue(){
        return value;
    }

    public double getVariation(){
        return variation;
    }

    public void setValue(double d){
        value = d;
    }

    public abstract void updateValue();
}