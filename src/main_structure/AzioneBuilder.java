package main_structure;

public class AzioneBuilder {
    private double startingValue;
    private double rangePer;
    private MonitorRendimenti monitor;

    public AzioneBuilder(double risk){
        rangePer = risk;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    public void setMonitor(MonitorRendimenti monitor) {
        this.monitor = monitor;
    }

    public Azione getResult(){
        return new Azione(rangePer, monitor, startingValue);
    }
}
