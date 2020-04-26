package main_structure;

public class AzioneBuilder {
    private double startingValue;
    private double rangePer;

    public AzioneBuilder(double risk){
        rangePer = risk;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    public Azione getResult(){
        return new Azione(rangePer, startingValue);
    }
}
