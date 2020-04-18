package main_structure;

import observer_classes.Observer;

public class Azione extends Titolo {
    private double maxVariationPer;
    private double maxIncPer = 0;
    private double maxDecPer = 0;

    public Azione(double maxVarPer, Observer o, double startValue){
        maxVariationPer = maxVarPer;
        generateMaxIncPer();
        generateMaxDecPer();
        value = startValue;
        observer = o;
    }

    private void generateMaxIncPer() {
        this.maxIncPer = Math.random() * maxVariationPer;
    }

    private void generateMaxDecPer() {
        this.maxDecPer = Math.random() * maxVariationPer;
    }

    @Override
    public void updateValue() {
        double variationPer = Math.random() * (maxIncPer + maxDecPer) - maxDecPer ;
        double newvalue = (variationPer/100 + 1) * value;
        variation = newvalue - value;
        value = newvalue;
        _notify(this);
    }
}
