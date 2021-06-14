package com.shichko.multithreading.entity;

import java.util.Optional;
import java.util.concurrent.Callable;

public class Ship implements Callable<Ship> {

    private long shipId;
    private int containersAmount;
    private int capacity;
    private ShipState shipState;
    private ShipOperation shipOperation;

    public Ship(long shipId, int containersAmount, int capacity, ShipOperation shipOperation) {
        this.shipId = shipId;
        this.containersAmount = containersAmount;
        this.capacity = capacity;
        this.shipOperation = shipOperation;
    }

    public Ship() {
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
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

    public ShipState getShipState() {
        return shipState;
    }

    public void setShipState(ShipState shipState) {
        this.shipState = shipState;
    }

    public ShipOperation getShipOperation() {
        return shipOperation;
    }

    public void setShipOperation(ShipOperation shipOperation) {
        this.shipOperation = shipOperation;
    }

    public void addContainerToShip() {
        containersAmount++;
    }

    public void removeContainerFromShip() {
        containersAmount--;
    }

    @Override
    public Ship call() {
        Port port = Port.getInstance();
        Optional<Dock> optionalDock = port.findDock();
        if (optionalDock.isPresent()) {
            Dock dock = optionalDock.get();
            dock.process(this);
            port.releaseDock(dock);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (shipId != ship.shipId) return false;
        if (containersAmount != ship.containersAmount) return false;
        if (capacity != ship.capacity) return false;
        if (shipState != ship.shipState) return false;
        return shipOperation == ship.shipOperation;
    }

    @Override
    public int hashCode() {
        int result = (int) (shipId ^ (shipId >>> 32));
        result = 31 * result + containersAmount;
        result = 31 * result + capacity;
        result = 31 * result + shipState.hashCode();
        result = 31 * result + shipOperation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("shipId=").append(shipId);
        sb.append(", containersAmount=").append(containersAmount);
        sb.append(", capacity=").append(capacity);
        sb.append(", shipState=").append(shipState);
        sb.append(", shipOperation=").append(shipOperation);
        sb.append('}');
        return sb.toString();
    }
}
