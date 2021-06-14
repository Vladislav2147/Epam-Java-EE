package com.shichko.multithreading.main;

import com.shichko.multithreading.entity.Port;
import com.shichko.multithreading.entity.Ship;
import com.shichko.multithreading.entity.ShipOperation;
import com.shichko.multithreading.entity.ShipState;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(1, 0, 1500, ShipState.NEW, ShipOperation.LOAD));
        ships.add(new Ship(1, 1500, 1500, ShipState.NEW, ShipOperation.UNLOAD));
//        ships.add(new Ship(2, 20, 45, ShipState.NEW, ShipOperation.UNLOAD));
//        ships.add(new Ship(3, 15, 50, ShipState.NEW, ShipOperation.LOAD));
//        ships.add(new Ship(4, 10, 33, ShipState.NEW, ShipOperation.LOAD));
//        ships.add(new Ship(5, 21, 25, ShipState.NEW, ShipOperation.UNLOAD));

        Port port = Port.getInstance();
        for (Ship ship: ships) {
            port.addShipToQueue(ship);
        }

        port.startProcessing();


    }
}
