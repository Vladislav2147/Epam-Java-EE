package com.shichko.composite.entity.impl;

import com.shichko.composite.entity.TextComponent;

public class TextCharacter implements TextComponent {

    private char value;
    private TextCharacterType type;

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }
}
