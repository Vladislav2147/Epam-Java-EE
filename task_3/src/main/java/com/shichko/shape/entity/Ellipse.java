package com.shichko.shape.entity;

import com.shichko.shape.observer.EllipseEvent;
import com.shichko.shape.observer.EllipseObservable;
import com.shichko.shape.observer.EllipseObserver;

public class Ellipse implements EllipseObservable {
    private long ellipseId;
    private Point firstPoint;
    private Point secondPoint;
    private EllipseObserver observer;

    public Ellipse(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint.clone();
        this.secondPoint = secondPoint.clone();
    }

    public long getEllipseId() {
        return ellipseId;
    }

    public void setEllipseId(long ellipseId) {
        this.ellipseId = ellipseId;
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
        if (!secondPoint.equals(ellipse.secondPoint)) return false;
        return observer != null ? observer.equals(ellipse.observer) : ellipse.observer == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (ellipseId ^ (ellipseId >>> 32));
        result = 31 * result + firstPoint.hashCode();
        result = 31 * result + secondPoint.hashCode();
        result = 31 * result + (observer != null ? observer.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ellipse{");
        sb.append("ellipseId=").append(ellipseId);
        sb.append(", firstPoint=").append(firstPoint);
        sb.append(", secondPoint=").append(secondPoint);
        sb.append(", observer=").append(observer);
        sb.append('}');
        return sb.toString();
    }
}
