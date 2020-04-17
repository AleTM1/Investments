package main_structure;
import observer_classes.Subject;

abstract class Titolo extends Subject {
    protected float value;

    public abstract float getValue();
    public abstract void updateValue();
}