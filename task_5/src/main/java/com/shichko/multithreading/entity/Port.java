package com.shichko.multithreading.entity;

import com.shichko.multithreading.exception.ShipPortException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private final static Logger logger = LogManager.getLogger();
    private static final int DOCKS_AMOUNT = 5;
    private static final int PORT_CAPACITY = 1000;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Port portInstance;

    private Deque<Ship> shipsQueue;
    private Deque<Dock> portDocks;
    private ReentrantLock portDocksLock = new ReentrantLock();
    private Condition portDocksCondition = portDocksLock.newCondition();
    private AtomicInteger containerCount = new AtomicInteger(PORT_CAPACITY / 2);

    private Port() {
        shipsQueue = new ArrayDeque<>();
        portDocks = new ArrayDeque<>(DOCKS_AMOUNT);
        for (int i = 0; i < DOCKS_AMOUNT; i++) {
            portDocks.add(new Dock(i));
        }
    }

    public static Port getInstance() {
        while (portInstance == null) {
            if (isCreated.compareAndSet(false, true)) {
                portInstance = new Port();
                logger.log(Level.INFO, "Port was created with " + portInstance.portDocks.size() + " docks");
            }
        }
        return portInstance;
    }

    //TODO delete maybe (replace with timer task)
    public void addContainerToPort() throws ShipPortException {
        if (containerCount.intValue() >= PORT_CAPACITY) {
            throw new ShipPortException("Unable to add container to port, because it's full");
        }
        containerCount.incrementAndGet();
    }

    public void removeContainerFromPort() throws ShipPortException {
        if (containerCount.intValue() <= 0) {
            throw new ShipPortException("Unable to remove container to port, because it's empty");
        }
        containerCount.decrementAndGet();
    }

    public Optional<Dock> getDock() {
        portDocksLock.lock();
        Dock dock = null;
        try {
            while ((dock = portDocks.poll()) == null) {
                portDocksCondition.await();
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException on thread " + Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        } finally {
            portDocksLock.unlock();
        }

        return Optional.ofNullable(dock);
    }

    public void releaseDock(Dock dock) {
        if (dock != null) {
            portDocksLock.lock();
            try {
                portDocks.add(dock);
                portDocksCondition.signal();
            } finally {
                portDocksLock.unlock();
            }
        }
    }
}
