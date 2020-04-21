package main_structure;

public class AzioneBuilder {
    private double startingValue;
    private double rangePer;
    private MonitorRendimenti monitor;

    public AzioneBuilder(MonitorRendimenti m){
        monitor = m;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    public void setRangePer(double rangePer) {
        this.rangePer = rangePer;
    }

    public Azione getResult(){
        return new Azione(rangePer, monitor, startingValue);
    }
}
