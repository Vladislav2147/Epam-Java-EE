package com.shichko.multithreading.entity;

public class Ship extends Thread {

    private final long shipId;
    private int containersAmount;
    private int capacity;
    private State shipState;

    public enum State {
        NEW, PROCESSING, FINISHED
    }

    public Ship(long shipId, int containersAmount, int capacity, State shipState) {
        this.shipId = shipId;
        this.containersAmount = containersAmount;
        this.capacity = capacity;
        this.shipState = shipState;
    }

    public long getShipId() {
        return shipId;
    }

    public int getContainersAmount() {
        return containersAmount;
    }

    public void setContainersAmount(int containersAmount) {
        this.containersAmount = containersAmount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public State getShipState() {
        return shipState;
    }

    public void setShipState(State shipState) {
        this.shipState = shipState;
    }

    public void addContainerToShip() {
        containersAmount++;
    }

    public void removeContainerFromShip() {
        containersAmount--;
    }

    @Override
    public void run() {//TODO without throws
        Port port = Port.getInstance();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (shipId != ship.shipId) return false;
        if (containersAmount != ship.containersAmount) return false;
        if (capacity != ship.capacity) return false;
        return shipState == ship.shipState;
    }

    @Override
    public int hashCode() {
        int result = (int) (shipId ^ (shipId >>> 32));
        result = 31 * result + containersAmount;
        result = 31 * result + capacity;
        result = 31 * result + shipState.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("shipId=").append(shipId);
        sb.append(", containersAmount=").append(containersAmount);
        sb.append(", capacity=").append(capacity);
        sb.append(", shipState=").append(shipState);
        sb.append('}');
        return sb.toString();
    }
}
