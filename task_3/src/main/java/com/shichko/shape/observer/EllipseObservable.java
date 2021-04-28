package com.shichko.shape.observer;

public interface EllipseObservable {
    void attach(EllipseObserver observer);
    void detach();
    void notifyObservers();
}
