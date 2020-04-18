package main_structure;

public class Azione extends Titolo {
    private static double maxVarPer = 5;
    private double variation = 0;
    private double maxIncPer = 0;
    private double maxDecPer = 0;

    public void generateMaxIncPer() {
        this.maxIncPer = Math.random() * maxVarPer;
    }

    public void generateMaxDecPer() {
        this.maxDecPer = Math.random() * maxVarPer;
    }

    public double getVariation() {
        return variation;
    }

    @Override
    public void updateValue() {
        double variationPer = Math.random() * (maxIncPer + maxDecPer) - maxDecPer ;
        double newvalue = (variationPer/100 + 1) * value;
        variation = newvalue - value;
        value = newvalue;
    }
}
