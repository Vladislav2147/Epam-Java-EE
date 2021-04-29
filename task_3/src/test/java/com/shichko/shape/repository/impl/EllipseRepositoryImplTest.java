package com.shichko.shape.repository.impl;

import com.shichko.shape.creator.EllipseCreator;
import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.repository.EllipseRepository;
import com.shichko.shape.repository.Specification;
import com.shichko.shape.repository.comparator.EllipseAreaDescendingComparator;
import com.shichko.shape.repository.comparator.EllipseFirstPointXComparator;
import com.shichko.shape.repository.comparator.EllipseIdComparator;
import com.shichko.shape.util.IdGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class EllipseRepositoryImplTest {

    private EllipseRepository repository;
    private List<Ellipse> ellipses;

    @BeforeMethod
    public void initRepository() throws EllipseException {
        IdGenerator.reset();
        repository = new EllipseRepositoryImpl();
        ellipses = new ArrayList<>();
        ellipses.add(EllipseCreator.createFromPoints(new Point(-1, 2), new Point(1, -2))); //Contains (0, 0)
        ellipses.add(EllipseCreator.createFromPoints(new Point(-2, 2), new Point(2, -2))); //Circle, Contains (0, 0)
        ellipses.add(EllipseCreator.createFromPoints(new Point(4, 6), new Point(6, 2)));
        ellipses.add(EllipseCreator.createFromPoints(new Point(-5, 2), new Point(1, -4))); //Circle, Contains (0, 0)
        ellipses.add(EllipseCreator.createFromPoints(new Point(-23, 5), new Point(6, 0)));
        ellipses.add(EllipseCreator.createFromPoints(new Point(0, 0), new Point(1000, -1000))); //Circle

        repository.addAll(ellipses);
    }

    @Test
    public void testSortById() {
        Comparator<Ellipse> idComparator = new EllipseIdComparator();
        repository.sort(idComparator);

        Ellipse ellipseWithMinId = ellipses.get(0);
        assertEquals(repository.get(0), ellipseWithMinId);
    }

    @Test
    public void testSortByFirstPointX() {
        Comparator<Ellipse> firstPointXComparator = new EllipseFirstPointXComparator();
        repository.sort(firstPointXComparator);

        Ellipse ellipseWithMinFirstPointX = ellipses.get(4);
        assertEquals(repository.get(0), ellipseWithMinFirstPointX);
    }

    @Test
    public void testSortByArea() {
        Comparator<Ellipse> areaDescendingComparator = new EllipseAreaDescendingComparator();
        repository.sort(areaDescendingComparator);

        Ellipse ellipseWithMaxArea = ellipses.get(5);

        assertEquals(repository.get(0), ellipseWithMaxArea);
    }

    @Test
    public void testQueryIdSpecificationReturnsEllipseById() {
        long id = 2;
        Specification idSpecification = new IdSpecification(id);

        Ellipse ellipseById = repository.query(idSpecification).stream().findFirst().get();
        assertEquals(ellipseById.getEllipseId(), id);
    }

    @Test
    public void testQueryCircleSpecificationReturnsOnlyCircles() {
        int expectedCirclesAmount = 3;
        Specification circleSpecification = new CircleSpecification();

        int actualCirclesAmount = repository.query(circleSpecification).size();
        assertEquals(actualCirclesAmount, expectedCirclesAmount);
    }

    @Test
    public void testQueryPointInsideEllipseReturnsOnlyEllipsesContainsPoint() {
        Point point = new Point(0,0);
        int expectedEllipsesContainsPointAmount = 3;
        Specification pointInsideEllipseSpecification = new PointInsideEllipseSpecification(point);

        int actualEllipsesContainsPointAmount = repository.query(pointInsideEllipseSpecification).size();
        assertEquals(actualEllipsesContainsPointAmount, expectedEllipsesContainsPointAmount);
    }
}