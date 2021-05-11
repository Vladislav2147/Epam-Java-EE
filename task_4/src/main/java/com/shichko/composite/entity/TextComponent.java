package com.shichko.composite.entity;

public interface TextComponent {

    void add(TextComponent textComponent);
    void remove(TextComponent textComponent);
    int size();
    TextComponent getChild(int index);
    String toString();

}
