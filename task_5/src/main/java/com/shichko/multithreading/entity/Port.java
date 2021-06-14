package com.shichko.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.shichko.multithreading.reader.PortPropertiesReader.*;

public class Port {

    private static final Logger logger = LogManager.getLogger();

    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Port portInstance;

    private Deque<Ship> shipsQueue;
    private Deque<Dock> portDocks;
    private ReentrantLock portDocksLock = new ReentrantLock();
    private Condition portDocksCondition = portDocksLock.newCondition();
    private AtomicInteger containerCount = new AtomicInteger((int)(PORT_CAPACITY * LOAD_FACTOR));

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

    public AtomicInteger getContainerCount() {
        return containerCount;
    }

    public void addShipToQueue(Ship ship) {
        shipsQueue.add(ship);
        ship.setShipState(ShipState.NEW);
    }

    public void startProcessing() {
        ExecutorService executorService = Executors.newFixedThreadPool(shipsQueue.size());
        while (!shipsQueue.isEmpty()) {
            executorService.submit(shipsQueue.poll());
        }
        executorService.shutdown();
    }

    public void addContainerToPort() {
        if (containerCount.intValue() >= PORT_CAPACITY) {
            containerCount.set((int)(PORT_CAPACITY * LOAD_FACTOR));
            logger.log(Level.WARN, "port is full, decreasing amount of containers");
        }
        containerCount.incrementAndGet();
    }

    public void removeContainerFromPort() {
        if (containerCount.intValue() <= 0) {
            containerCount.set((int)(PORT_CAPACITY * LOAD_FACTOR));
            logger.log(Level.WARN, "port is empty, increasing amount of containers");
        }
        containerCount.decrementAndGet();
    }

    public Optional<Dock> findDock() {
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
        logger.log(Level.INFO, "Dock found: " + dock);
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
            logger.log(Level.INFO, "Dock released: " + dock);
        }
    }
}
