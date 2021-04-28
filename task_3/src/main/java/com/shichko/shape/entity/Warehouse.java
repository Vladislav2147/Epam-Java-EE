package com.shichko.shape.entity;

import com.shichko.shape.exception.EllipseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final static Logger logger = LogManager.getLogger();
    private static Warehouse instance;
    private Map<Long, EllipseParameters> ellipseMap = new HashMap<>();

    private Warehouse() { }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public EllipseParameters get(Object key) {
        EllipseParameters params = ellipseMap.get(key);
        logger.log(Level.INFO, "get params " + params + " by id " + key);
        return params;
    }

    public EllipseParameters put(Long key, EllipseParameters value) {
        EllipseParameters params = ellipseMap.put(key, value);
        logger.log(Level.INFO, "put params " + value + "to id " + key + "; returned " + params);
        return params;
    }

    public void updateParamsById(long id, double perimeter, double area) throws EllipseException {
        EllipseParameters parameters = get(id);
        if (parameters == null) {
            throw new EllipseException("params by id" + id + " are not found in warehouse");
        }
    }

    public EllipseParameters remove(Object key) {
        return ellipseMap.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warehouse warehouse = (Warehouse) o;

        return ellipseMap != null ? ellipseMap.equals(warehouse.ellipseMap) : warehouse.ellipseMap == null;
    }

    @Override
    public int hashCode() {
        return ellipseMap != null ? ellipseMap.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{");
        sb.append("ellipseMap=").append(ellipseMap);
        sb.append('}');
        return sb.toString();
    }
}
