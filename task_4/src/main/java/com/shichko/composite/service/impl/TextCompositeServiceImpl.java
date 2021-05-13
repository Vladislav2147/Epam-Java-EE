package com.shichko.composite.service.impl;

import com.shichko.composite.comparator.TextComponentSizeComparator;
import com.shichko.composite.entity.*;
import com.shichko.composite.exception.TextCompositeException;
import com.shichko.composite.service.TextCompositeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextCompositeServiceImpl implements TextCompositeService {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void sortParagraphsBySentencesAmount(TextComposite text) throws TextCompositeException {

        TextCompositeType type = text.getCompositeType();

        if (type != TextCompositeType.TEXT) {
            throw new TextCompositeException("Argument should have type TEXT, but found: " + type.name());
        }

        List<TextComponent> paragraphs = text.getComponents();
        paragraphs.sort(new TextComponentSizeComparator());
        text.setComponents(paragraphs);

        logger.log(Level.INFO, "Text with sorted paragraphs by sentence amount: \n" + text);
    }

    @Override
    public Optional<TextComposite> findSentenceWithLongestWord(TextComposite text) throws TextCompositeException {

        TextCompositeType type = text.getCompositeType();

        if (type != TextCompositeType.TEXT) {
            throw new TextCompositeException("Argument should have type TEXT, but found: " + type.name());
        }

        List<TextComposite> sentences = findCompositeChildrenByType(text, TextCompositeType.SENTENCE);

        Optional<TextComposite> optionalSentenceWithLongestWord = sentences
                .stream()
                .max(Comparator.comparingInt(this::maxWordLengthInSentence));

        if (optionalSentenceWithLongestWord.isPresent()) {
            logger.log(Level.INFO, "Sentence with longest word is: " + optionalSentenceWithLongestWord.get() + " in text " + text);
        } else {
            logger.log(Level.INFO, "Sentence with longest word not found in text" + text);
        }

        return optionalSentenceWithLongestWord;
    }

    @Override
    public void removeSentencesIfWordsCountLessThanMinValue(TextComposite text, int minValue) throws TextCompositeException {

        TextCompositeType type = text.getCompositeType();

        if (type != TextCompositeType.TEXT) {
            throw new TextCompositeException("Argument should have type TEXT, but found: " + type.name());
        }

        List<TextComposite> paragraphs = findCompositeChildrenByType(text, TextCompositeType.PARAGRAPH);
        for (TextComposite paragraph: paragraphs) {
            List<TextComponent> sentences = paragraph.getComponents();
            for (Iterator<TextComponent> iterator = sentences.iterator(); iterator.hasNext();) {
                TextComponent sentence = iterator.next();
                int wordsCount = findCompositeChildrenByType((TextComposite) sentence, TextCompositeType.WORD).size();
                if (wordsCount < minValue) {
                    iterator.remove();
                    logger.log(Level.INFO, "sentence was removed: " + sentence);
                }
            }
            paragraph.setComponents(sentences);
        }
    }

    @Override
    public int countRepeatedWords(TextComposite text) throws TextCompositeException {

        TextCompositeType type = text.getCompositeType();

        if (type != TextCompositeType.TEXT) {
            throw new TextCompositeException("Argument should have type TEXT, but found: " + type.name());
        }

        List<String> words = findCompositeChildrenByType(text, TextCompositeType.WORD)
                .stream()
                .map(word -> word.toString().toLowerCase())
                .collect(Collectors.toList());

        Map<String, Integer> wordEntranceCounter = new HashMap<>();
        for (String word: words) {
            int newValue = wordEntranceCounter.getOrDefault(word, 0) + 1;
            wordEntranceCounter.put(word, newValue);
        }

        int repeatedWordsAmount = 0;
        for (int value : wordEntranceCounter.values()) {
            if (value > 1) {
                repeatedWordsAmount++;
            }
        }

        logger.log(Level.INFO, "Repeated words count: " + repeatedWordsAmount + " in text: " + text);

        return repeatedWordsAmount;
    }

    @Override
    public int countVowels(TextComposite sentence) throws TextCompositeException {

        TextCompositeType type = sentence.getCompositeType();

        if (type != TextCompositeType.SENTENCE) {
            throw new TextCompositeException("Argument should have type SENTENCE, but found: " + type.name());
        }

        String vowels = "aeiouy";

        List<TextCharacter> letters = findCharacterChildrenByType(sentence, TextCharacterType.LETTER);

        int vowelsCount = 0;
        for(TextCharacter letter: letters) {
            String formattedLetter = String.valueOf(letter.getValue()).toLowerCase();
            if (vowels.contains(formattedLetter)) {
                vowelsCount++;
            }
        }

        logger.log(Level.INFO, "vowels count: " + vowelsCount + " in sentence: " + sentence);

        return vowelsCount;
    }

    @Override
    public int countConsonants(TextComposite sentence) throws TextCompositeException {

        TextCompositeType type = sentence.getCompositeType();

        if (type != TextCompositeType.SENTENCE) {
            throw new TextCompositeException("Argument should have type SENTENCE, but found: " + type.name());
        }

        String consonants = "qwrtpsdfghjklzxcvbnm";

        List<TextCharacter> letters = findCharacterChildrenByType(sentence, TextCharacterType.LETTER);

        int consonantsCount = 0;
        for(TextCharacter letter: letters) {
            String formattedLetter = String.valueOf(letter.getValue()).toLowerCase();
            if (consonants.contains(formattedLetter)) {
                consonantsCount++;
            }
        }

        logger.log(Level.INFO, "consostants count: " + consonantsCount + " in sentence: " + sentence);

        return consonantsCount;
    }

    public List<TextComposite> findCompositeChildrenByType(TextComposite component, TextCompositeType type) {

        List<TextComposite> children = new ArrayList<>();

        for (int i = 0; i < component.size(); i++) {
            TextComponent child = component.getChild(i);
            if (child instanceof TextComposite) {
                TextComposite compositeChild = (TextComposite)child;
                if (compositeChild.getCompositeType().equals(type)) {
                    children.add(compositeChild);
                    logger.log(Level.INFO, "Child with type " + type.name() + " found: \n" + child);
                }
                children.addAll(findCompositeChildrenByType(compositeChild, type));
            }
        }

        return children;
    }

    public List<TextCharacter> findCharacterChildrenByType(TextComponent component, TextCharacterType type) {

        List<TextCharacter> children = new ArrayList<>();

        for (int i = 0; i < component.size(); i++) {
            TextComponent child = component.getChild(i);
            if (child instanceof TextCharacter) {
                TextCharacter characterChild = (TextCharacter)child;
                if (characterChild.getCharacterType().equals(type)) {
                    children.add(characterChild);
                    logger.log(Level.INFO, "Child with type " + type.name() + " found: \n" + child);
                }
            } else {
                children.addAll(findCharacterChildrenByType(child, type));
            }
        }

        return children;
    }

    private int maxWordLengthInSentence(TextComposite sentence) {
        List<TextComposite> words = findCompositeChildrenByType(sentence, TextCompositeType.WORD);

        int maxLength = words
                .stream()
                .max(new TextComponentSizeComparator())
                .map(TextComposite::size)
                .orElse(0);
        logger.log(Level.INFO, "Max word length is " + maxLength + " in sentence: " + sentence);
        return maxLength;
    }

}
