package com.shichko.composite.parser.impl;

import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.entity.TextCompositeType;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;

public class SentenceParser implements InformationParser {

    private final static String LEXEME_DELIMITER = "(?<=\\s)";
    private final static InformationParser lexemeParser = new LexemeParser();

    @Override
    public TextComponent parse(String sentenceText) throws TextCompositeException {

        TextComponent sentenceComponent = new TextComposite(TextCompositeType.SENTENCE);
        String[] lexemes = sentenceText.split(LEXEME_DELIMITER);

        for (String lexeme: lexemes) {
            TextComponent lexemeComponent = lexemeParser.parse(lexeme);
            sentenceComponent.add(lexemeComponent);
        }

        return sentenceComponent;
    }
}
