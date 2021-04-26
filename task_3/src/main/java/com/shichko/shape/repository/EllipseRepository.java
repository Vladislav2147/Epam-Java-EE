package com.shichko.shape.repository;

import com.shichko.shape.entity.Ellipse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EllipseRepository {
    private List<Ellipse> ellipses;

    public boolean remove(Object o) {
        return ellipses.remove(o);
    }

    public boolean addAll(Collection<? extends Ellipse> c) {
        return ellipses.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return ellipses.removeAll(c);
    }

    public Ellipse get(int index) {
        return ellipses.get(index);
    }

    public Ellipse set(int index, Ellipse element) {
        return ellipses.set(index, element);
    }

    public int indexOf(Object o) {
        return ellipses.indexOf(o);
    }

    public List<Ellipse> query(Specification specification) {
        List<Ellipse> list = ellipses
                .stream()
                .filter(specification::specify)
                .collect(Collectors.toList());;
        return list;
    }
}
