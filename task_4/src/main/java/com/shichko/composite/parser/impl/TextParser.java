package com.shichko.composite.parser.impl;

import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.entity.TextCompositeType;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;

public class TextParser implements InformationParser {

    private final static String PARAGRAPH_DELIMITER = "(?=\\n\\r?(\\t|\\s{4}))";
    private final static InformationParser paragraphParser = new ParagraphParser();

    @Override
    public TextComponent parse(String text) throws TextCompositeException {

        TextComponent composite = new TextComposite(TextCompositeType.TEXT);
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = paragraphParser.parse(paragraph);
            composite.add(paragraphComponent);
        }

        return composite;
    }
}
