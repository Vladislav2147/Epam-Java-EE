package com.shichko.multithreading.entity;

public class Dock {

    private final long dockId;

    public Dock(long dockId) {
        this.dockId = dockId;
    }

    public long getDockId() {
        return dockId;
    }
}
