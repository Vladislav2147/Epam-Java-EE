package com.shichko.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private final TextCompositeType compositeType;
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextCompositeType compositeType) {
        this.compositeType = compositeType;
    }

    public TextComposite(TextCompositeType compositeType, List<TextComponent> components) {
        this.compositeType = compositeType;
        this.components = components;
    }

    public TextCompositeType getCompositeType() {
        return compositeType;
    }

    public List<TextComponent> getComponents() {
        return new ArrayList<>(components);
    }

    public void setComponents(List<TextComponent> components) {
        this.components = components;
    }

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public TextComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        if (compositeType != that.compositeType) return false;
        return components.equals(that.components);
    }

    @Override
    public int hashCode() {
        int result = compositeType.hashCode();
        result = 31 * result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (TextComponent child: components) {
            sb.append(child);
        }
        return sb.toString();
    }
}
