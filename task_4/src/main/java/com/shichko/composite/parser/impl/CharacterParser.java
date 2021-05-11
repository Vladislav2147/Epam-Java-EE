package com.shichko.composite.parser.impl;

import com.shichko.composite.entity.TextCharacter;
import com.shichko.composite.entity.TextCharacterType;
import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;

public class CharacterParser implements InformationParser {

    private final static String LETTER_REGEX = "^[a-zA-Z]$";

    @Override
    public TextComponent parse(String text) throws TextCompositeException {
        if (text.length() != 1) {
            throw new TextCompositeException("Character can't have length != 1: " + text);
        }
        TextCharacterType characterType;
        if (text.matches(LETTER_REGEX)) {
            characterType = TextCharacterType.LETTER;
        } else {
            characterType = TextCharacterType.SYMBOL;
        }
        TextComponent characterComponent = new TextCharacter(text.charAt(0), characterType);
        return characterComponent;
    }
}
