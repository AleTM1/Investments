package main_structure;

class AzioneBuilder {
    private double startingValue;
    private double rangePer;
    private MonitorRendimenti monitor;

    AzioneBuilder(MonitorRendimenti m){
        monitor = m;
    }

    void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    void setRangePer(double rangePer) {
        this.rangePer = rangePer;
    }

    Azione getResult(){
        Azione azione = new Azione(rangePer, monitor, startingValue);
        System.out.println("Azione generata");
        return azione;
    }
}
