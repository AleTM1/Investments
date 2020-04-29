package main_structure;

public class Azione extends Titolo {
    private double maxIncPer = 0;
    private double maxDecPer = 0;

    public Azione(double maxVarPer, double startValue){
        generateMaxIncPer(maxVarPer);
        generateMaxDecPer(maxVarPer);
        value = startValue;
    }

    private void generateMaxIncPer(double maxVariationPer) {
        this.maxIncPer = Math.random() * maxVariationPer;
    }

    private void generateMaxDecPer(double maxVariationPer) {
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