package main_structure;
import observer_classes.Subject;

abstract public class Titolo extends Subject {
    double value = 0;
    double variation = 0;

    public final double getValue(){
        return value;
    }

    public final double getVariation(){
        return variation;
    }

    final void setValue(double v){
        value = v;
    }

    public abstract void updateValue();
}