package com.shichko.multithreading.entity;

import com.shichko.multithreading.exception.ShipPortException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Dock {

    private final static Logger logger = LogManager.getLogger();
    private final long dockId;

    public Dock(long dockId) {
        this.dockId = dockId;
    }

    public long getDockId() {
        return dockId;
    }

    public void process(Ship ship) {
        Port port = Port.getInstance();
        ShipOperation operation = ship.getShipOperation();
        try {
            switch (operation) {
                case LOAD:
                    while (ship.getContainersAmount() < ship.getCapacity()) {
                        port.removeContainerFromPort();
                        TimeUnit.MILLISECONDS.sleep(100);
                        ship.addContainerToShip();
                    }
                    break;
                case UNLOAD:
                    while (ship.getContainersAmount() > 0) {
                        ship.removeContainerFromShip();
                        TimeUnit.MILLISECONDS.sleep(100);
                        port.addContainerToPort();
                    }
                    break;
            }
        } catch (ShipPortException e) {
            logger.log(Level.ERROR, e);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException on thread " + Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dock dock = (Dock) o;

        return dockId == dock.dockId;
    }

    @Override
    public int hashCode() {
        return (int) (dockId ^ (dockId >>> 32));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dock{");
        sb.append("dockId=").append(dockId);
        sb.append('}');
        return sb.toString();
    }
}
