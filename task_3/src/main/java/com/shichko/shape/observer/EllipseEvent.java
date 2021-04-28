package com.shichko.shape.observer;

import com.shichko.shape.entity.Ellipse;

import java.util.EventObject;

public class EllipseEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public EllipseEvent(Ellipse source) {
        super(source);
    }

    @Override
    public Ellipse getSource() {
        return (Ellipse)super.getSource();
    }
}
