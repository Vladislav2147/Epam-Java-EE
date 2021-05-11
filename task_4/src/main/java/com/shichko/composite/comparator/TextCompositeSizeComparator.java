package com.shichko.composite.comparator;

import com.shichko.composite.entity.TextComposite;

import java.util.Comparator;

public class TextCompositeSizeComparator implements Comparator<TextComposite> {
    @Override
    public int compare(TextComposite composite1, TextComposite composite2) {
        return composite1.size() - composite2.size();
    }
}
