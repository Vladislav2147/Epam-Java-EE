package com.shichko.shape.repository.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.repository.EllipseRepository;
import com.shichko.shape.repository.Specification;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EllipseRepositoryImpl implements EllipseRepository {

    private static EllipseRepositoryImpl instance;
    private List<Ellipse> ellipses = new ArrayList<>();

    private EllipseRepositoryImpl() {

    }

    public static EllipseRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new EllipseRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Ellipse> getAll() {
        return new ArrayList<>(ellipses);
    }

    @Override
    public int size() {
        return ellipses.size();
    }

    @Override
    public void forEach(Consumer<Ellipse> action) {
        ellipses.forEach(action);
    }

    @Override
    public boolean remove(Ellipse ellipse) {
        return ellipses.remove(ellipse);
    }

    @Override
    public boolean addAll(Collection<Ellipse> ellipses) {
        return this.ellipses.addAll(ellipses);
    }

    @Override
    public boolean add(Ellipse ellipse) {
        return this.ellipses.add(ellipse);
    }

    @Override
    public boolean removeAll(Collection<Ellipse> ellipses) {
        return this.ellipses.removeAll(ellipses);
    }

    @Override
    public Ellipse get(int index) {
        return ellipses.get(index);
    }

    @Override
    public Ellipse set(int index, Ellipse element) {
        return ellipses.set(index, element);
    }

    @Override
    public int indexOf(Ellipse ellipse) {
        return ellipses.indexOf(ellipse);
    }

    @Override
    public void clear() {
        ellipses.clear();
    }

    @Override
    public void sort(Comparator<Ellipse> comparator) {
        ellipses.sort(comparator);
    }

    @Override
    public List<Ellipse> query(Specification specification) {
        List<Ellipse> list = ellipses
                .stream()
                .filter(specification::specify)
                .collect(Collectors.toList());
        return list;
    }
}
