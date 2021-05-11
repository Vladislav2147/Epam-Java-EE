package com.shichko.composite.parser;

import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.exception.TextCompositeException;

public interface InformationParser {
    TextComponent parse(String text) throws TextCompositeException;
}
