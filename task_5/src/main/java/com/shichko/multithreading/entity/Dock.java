package com.shichko.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static com.shichko.multithreading.reader.PortPropertiesReader.PROCESSING_TIME_MILLIS;

public class Dock {

    private static final Logger logger = LogManager.getLogger();

    private final long dockId;

    public Dock(long dockId) {
        this.dockId = dockId;
    }

    public long getDockId() {
        return dockId;
    }

    public void process(Ship ship) {
        Port port = Port.getInstance();
        ship.setShipState(ShipState.PROCESSING);
        ShipOperation operation = ship.getShipOperation();
        try {
            switch (operation) {
                case LOAD:
                    while (ship.getContainersAmount() < ship.getCapacity()) {
                        port.removeContainerFromPort();
                        TimeUnit.MILLISECONDS.sleep(PROCESSING_TIME_MILLIS);
                        ship.addContainerToShip();
                    }
                    logger.log(Level.INFO, "Successful load of ship: " + ship + " at dock: " + this +
                            ". Containers in the port: " + port.getContainerCount());
                    break;
                case UNLOAD:
                    while (ship.getContainersAmount() > 0) {
                        ship.removeContainerFromShip();
                        TimeUnit.MILLISECONDS.sleep(PROCESSING_TIME_MILLIS);
                        port.addContainerToPort();
                    }
                    logger.log(Level.INFO, "Successful unload of ship: " + ship + " at dock: " + this +
                            ". Containers in the port: " + port.getContainerCount());
                    break;
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException on thread " + Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        }
        ship.setShipState(ShipState.FINISHED);
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
