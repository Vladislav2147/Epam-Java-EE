package com.shichko.composite.parser.impl;

import com.shichko.composite.entity.TextComponent;
import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.entity.TextCompositeType;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class LexemeParser implements InformationParser {

    private final static String WORD_AND_PUNCTUATION_REGEX = "([a-zA-Z]+)|([^a-zA-Z])";
    private final static String WORD_DELIMITER = "[a-zA-Z]+";
    private final static InformationParser wordParser = new WordParser();
    private final static InformationParser characterParser = new CharacterParser();

    @Override
    public TextComponent parse(String lexemeText) throws TextCompositeException {

        TextComponent lexemeComponent = new TextComposite(TextCompositeType.LEXEME);
        String[] wordsAndPunctuation = Pattern.compile(WORD_AND_PUNCTUATION_REGEX)
                .matcher(lexemeText)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);

        for (String item: wordsAndPunctuation) {
            TextComponent wordOrPunctuationComponent;
            if (item.matches(WORD_DELIMITER)) {
                wordOrPunctuationComponent = wordParser.parse(item);
            } else {
                wordOrPunctuationComponent = characterParser.parse(item);
            }

            lexemeComponent.add(wordOrPunctuationComponent);
        }

        return lexemeComponent;
    }
}
