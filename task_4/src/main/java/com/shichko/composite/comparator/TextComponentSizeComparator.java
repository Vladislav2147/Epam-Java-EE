package com.shichko.composite.comparator;

import com.shichko.composite.entity.TextComponent;

import java.util.Comparator;

public class TextComponentSizeComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent component1, TextComponent component2) {
        return component1.size() - component2.size();
    }
}
