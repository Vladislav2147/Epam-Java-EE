package com.shichko.composite.parser.impl;

import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.entity.TextCompositeType;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;

public class WordParser implements InformationParser {

    private final static String LETTER_DELIMITER = "";
    private final static InformationParser characterParser = new CharacterParser();

    @Override
    public TextComponent parse(String wordText) throws TextCompositeException {

        TextComponent wordComponent = new TextComposite(TextCompositeType.WORD);
        String[] letters = wordText.split(LETTER_DELIMITER);

        for (String letter: letters) {
            TextComponent letterComponent = characterParser.parse(letter);
            wordComponent.add(letterComponent);
        }

        return wordComponent;
    }
}
