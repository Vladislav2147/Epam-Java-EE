package com.shichko.shape.repository;

import com.shichko.shape.entity.Ellipse;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public interface EllipseRepository {

    List<Ellipse> getAll();

    int size();

    void forEach(Consumer<Ellipse> action);

    boolean remove(Ellipse ellipse);

    boolean addAll(Collection<Ellipse> ellipses);

    boolean add(Ellipse ellipse);

    boolean removeAll(Collection<Ellipse> ellipses);

    Ellipse get(int index);

    Ellipse set(int index, Ellipse element);

    int indexOf(Ellipse ellipse);

    void clear();

    void sort(Comparator<Ellipse> comparator);

    List<Ellipse> query(Specification specification);
}
