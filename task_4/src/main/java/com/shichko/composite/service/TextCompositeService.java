package com.shichko.composite.service;

import com.shichko.composite.entity.TextComposite;
import com.shichko.composite.exception.TextCompositeException;

import java.util.Optional;

public interface TextCompositeService {

    void sortParagraphsBySentencesAmount(TextComposite text) throws TextCompositeException;

    Optional<TextComposite> findSentenceWithLongestWord(TextComposite text) throws TextCompositeException;

    void removeSentencesIfWordsCountLessThanMinValue(TextComposite text, int minValue) throws TextCompositeException;

    int countRepeatedWords(TextComposite text) throws TextCompositeException;

    int countVowels(TextComposite sentence) throws TextCompositeException;

    int countConsonants(TextComposite sentence) throws TextCompositeException;

}
