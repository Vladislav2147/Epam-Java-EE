package com.shichko.composite.parser.impl;

import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.entity.TextCompositeType;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;

public class ParagraphParser implements InformationParser {

    private final static String SENTENCE_DELIMITER = "(?<=[.!?…]\\s)";
    private final static InformationParser sentenceParser = new SentenceParser();

    @Override
    public TextComponent parse(String paragraphText) throws TextCompositeException {

        TextComponent paragraphComponent = new TextComposite(TextCompositeType.PARAGRAPH);
        String[] sentences = paragraphText.split(SENTENCE_DELIMITER);

        for (String sentence: sentences) {
            TextComponent sentenceComponent = sentenceParser.parse(sentence);
            paragraphComponent.add(sentenceComponent);
        }

        return paragraphComponent;
    }
}
