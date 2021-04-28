package com.shichko.shape.entity;

import com.shichko.shape.observer.EllipseEvent;
import com.shichko.shape.observer.EllipseObservable;
import com.shichko.shape.observer.EllipseObserver;
import com.shichko.shape.util.IdGenerator;

public class Ellipse implements EllipseObservable {
    private long ellipseId;
    private Point firstPoint;
    private Point secondPoint;
    private EllipseObserver observer;

    public Ellipse(Point firstPoint, Point secondPoint) {
        //TODO remove it?
        this.ellipseId = IdGenerator.generateId();
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public long getEllipseId() {
        return ellipseId;
    }

    public Point getFirstPoint() {
        return firstPoint.clone();
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
        notifyObservers();
    }

    public Point getSecondPoint() {
        return secondPoint.clone();
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
        notifyObservers();
    }

    @Override
    public void attach(EllipseObserver observer) {
        this.observer = observer;
    }

    @Override
    public void detach() {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            EllipseEvent event = new EllipseEvent(this);
            observer.parameterChanged(event);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ellipse ellipse = (Ellipse) o;

        if (ellipseId != ellipse.ellipseId) return false;
        if (!firstPoint.equals(ellipse.firstPoint)) return false;
        return secondPoint.equals(ellipse.secondPoint);
    }

    @Override
    public int hashCode() {
        int result = (int) (ellipseId ^ (ellipseId >>> 32));
        result = 31 * result + firstPoint.hashCode();
        result = 31 * result + secondPoint.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ellipse{");
        sb.append("ellipseId=").append(ellipseId);
        sb.append(", p1=").append(firstPoint);
        sb.append(", p2=").append(secondPoint);
        sb.append('}');
        return sb.toString();
    }
}
