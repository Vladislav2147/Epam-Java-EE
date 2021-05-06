package com.shichko.composite.entity;

public interface TextComponent {

    void add(TextComponent textComponent);
    void remove(TextComponent textComponent);
    TextComponent getChild(int index);

}
