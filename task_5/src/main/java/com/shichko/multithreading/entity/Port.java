package com.shichko.multithreading.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final int DOCKS_AMOUNT = 5;
    private static final int PORT_CAPACITY = 1000;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Port portInstance;

    private Deque<Dock> portDocks;
    private ReentrantLock portDocksLock = new ReentrantLock();
    private Condition portDocksCondition = portDocksLock.newCondition();
    private final AtomicInteger containerCount = new AtomicInteger(PORT_CAPACITY / 2);
    //TODO atomic boolean isCreated & atomic int count

    private Port() {
        portDocks = new ArrayDeque<>(DOCKS_AMOUNT);
        for (int i = 0; i < DOCKS_AMOUNT; i++) {
            portDocks.add(new Dock(i));
        }
    }

    public static Port getInstance() {
        if (portInstance == null) {
            if (isCreated.compareAndSet(false, true)) {
                portInstance = new Port();
            }
        }
        return portInstance;
    }

    public Optional<Dock> getDock() {
        portDocksLock.lock();
        Dock dock = null;
        try {
            while ((dock = portDocks.poll()) == null) {
                portDocksCondition.await();
            }
        } catch (InterruptedException e) {

        }finally {
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
