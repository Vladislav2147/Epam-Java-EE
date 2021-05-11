package com.shichko.composite.service.impl;

import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.entity.TextCompositeType;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.parser.InformationParser;
import com.shichko.composite.parser.impl.SentenceParser;
import com.shichko.composite.parser.impl.TextParser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TextCompositeServiceImplTest {

    private TextCompositeServiceImpl service;
    private TextParser parser;

    @BeforeTest
    public void initService() {
        service = new TextCompositeServiceImpl();
        parser = new TextParser();
    }

    @Test
    public void testSortParagraphsBySentencesAmount() throws TextCompositeException {

        String paragraph1 = "    1 sentence. 2 sentence.\n";
        String paragraph2 = "    1 sentence. 2 sentence. 3 sentence.\n";
        String paragraph3 = "    1 sentence.\n";

        StringBuilder unsortedParagraphs = new StringBuilder();
        unsortedParagraphs.append(paragraph1).append(paragraph2).append(paragraph3);

        StringBuilder sortedParagraphs = new StringBuilder();
        sortedParagraphs.append(paragraph3).append(paragraph1).append(paragraph2);

        String sortedParagraphsString = sortedParagraphs.toString().replace("\n", "");

        TextComposite textComposite = (TextComposite) parser.parse(unsortedParagraphs.toString());

        service.sortParagraphsBySentencesAmount(textComposite);

        String actualSortedParagraphs = textComposite.toString().replace("\n", "");

        assertEquals(actualSortedParagraphs, sortedParagraphsString);
    }


    @Test
    public void testFindSentenceWithLongestWord() throws TextCompositeException {

        String text = "\tfirst sentence. sentence with looooooooooongest word. Last sentence?";
        String sentenceWithLongestWord = "sentence with looooooooooongest word. ";

        TextComposite textComposite = (TextComposite) parser.parse(text);
        String actual = service.findSentenceWithLongestWord(textComposite).get().toString();

        assertEquals(actual, sentenceWithLongestWord);
    }

    @Test
    public void testRemoveSentencesIfWordsCountLessThanMinValue() throws TextCompositeException {

        String text = "\tfirst sentence with 4 words. second sentence with 4 words. Too short sentence.";
        int sentenceCount = 2;

        TextComposite textComposite = (TextComposite) parser.parse(text);
        service.removeSentencesIfWordsCountLessThanMinValue(textComposite, 4);

        int actualSentenceCount = service.findCompositeChildrenByType(textComposite, TextCompositeType.SENTENCE).size();
        assertEquals(actualSentenceCount, sentenceCount);
    }

    @Test
    public void testCountRepeatedWords() throws TextCompositeException {

        String text = "Some word some word Word yep.";
        int repeatedWords = 2;

        TextComposite textComposite = (TextComposite) parser.parse(text);
        int actualRepeatedWords = service.countRepeatedWords(textComposite);

        assertEquals(actualRepeatedWords, repeatedWords);

    }

    @Test
    public void testCountVowels() throws TextCompositeException {
        String text = "Some word some word Word yep.";
        int vowelsCount = 9;
        InformationParser sentenceParser = new SentenceParser();

        TextComposite sentence = (TextComposite) sentenceParser.parse(text);
        int actualVowelsCount = service.countVowels(sentence);

        assertEquals(actualVowelsCount, vowelsCount);
    }

    @Test
    public void testCountConsonants() throws TextCompositeException {
        String text = "Some word some word Word yep.";
        int consonantsCount = 14;
        InformationParser sentenceParser = new SentenceParser();

        TextComposite sentence = (TextComposite) sentenceParser.parse(text);
        int actualConsonantsCount = service.countConsonants(sentence);

        assertEquals(actualConsonantsCount, consonantsCount);
    }
}