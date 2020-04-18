package observer_classes;

public abstract class Subject {
    protected Observer observer;

    public void addObserver(Observer o){
        observer = o;
    }

    protected void _notify(Object o, double v){
        observer.update(o, v);
    }
}
