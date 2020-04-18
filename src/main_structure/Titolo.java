package main_structure;
import observer_classes.Subject;

abstract class Titolo extends Subject {
    double value = 0;

    public double getValue(){
        return value;
    }

    public void setValue(double d){
        value = d;
    }

    public abstract void updateValue();
}