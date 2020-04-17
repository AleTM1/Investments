package observer_classes;

public abstract class Subject {
    protected Observer observer;

    public void addObserver(Observer o){
        observer = o;
    }
    public void _notify(){
        observer.update();
    }
}
