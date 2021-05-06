package com.shichko.composite.entity.impl;

import com.shichko.composite.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private TextCompositeType type;
    private List<TextComponent> components = new ArrayList<>();

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public TextComponent getChild(int index) {
        return components.get(index);
    }
}
