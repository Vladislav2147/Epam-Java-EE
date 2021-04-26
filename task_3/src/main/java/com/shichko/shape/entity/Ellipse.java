package com.shichko.shape.entity;

public class Ellipse {
    private int ellipseId;
    private Point firstPoint;
    private Point secondPoint;

    public Ellipse(int ellipseId, Point firstPoint, Point secondPoint) {
        this.ellipseId = ellipseId;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public int getEllipseId() {
        return ellipseId;
    }

    public void setEllipseId(int ellipseId) {
        this.ellipseId = ellipseId;
    }

    public Point getFirstPoint() {
        return new Point(firstPoint);
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return new Point(secondPoint);
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
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
        int result = ellipseId;
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
