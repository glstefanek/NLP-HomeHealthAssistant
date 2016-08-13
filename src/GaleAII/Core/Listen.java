/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GaleAII.Core;

import com.aliasi.dict.MapDictionary;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.dict.DictionaryEntry;
import com.aliasi.dict.MapDictionary;
import com.aliasi.dict.TrieDictionary;
import com.aliasi.dict.Dictionary;
import com.aliasi.dict.ExactDictionaryChunker;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.util.AbstractExternalizable;
import java.io.File;
import GaleAII.Language.*;
import GaleAII.Core.Speak;

/**
 *
 * @author Brian Miller
 */
public class Listen {

    ArrayList<ExactDictionary> dictionaries = new ArrayList<>();
    Chunker nerModelChunker;
    Speak speak;

    public Listen(Speak speak) throws Exception {
        fillDictionaries();
        populateNerChunker();
        this.speak = speak;
    }

    /**
     * Dictionaries are used by the processor to tag words and phrases in
     * sentences with words and phrases found in the listed dictionaries. These
     * only match the word or phrase exactly.
     *
     * @throws Exception on file not found
     */
    private void fillDictionaries() throws Exception {
        try {
            dictionaries.add(new ExactDictionary("Allergy", "Allergy.txt"));
            dictionaries.add(new ExactDictionary("Family", "Family.txt"));
            dictionaries.add(new ExactDictionary("Illness", "Illnesses.txt"));
            dictionaries.add(new ExactDictionary("Immunization", "Immunizations.txt"));
            dictionaries.add(new ExactDictionary("Answer", "Answer.txt"));
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Populates the Ner model for later processing.
     */
    private void populateNerChunker() {
        File nerModel = new File("Resources/ne-en-news-muc6.AbstractCharLmRescoringChunker");
        try {
            Chunker chunker = (Chunker) AbstractExternalizable.readObject(nerModel);
            nerModelChunker = chunker;
            System.out.println("Ner chunker created");
        } catch (Exception e) {
        }
    }
    /**
     * Returns a tagged sentence.
     * @param stringToConvert
     * @return 
     */
    public Sentence convertStringToSentence(String stringToConvert){
        Sentence newSentence = new Sentence(stringToConvert);
        tagSentence(newSentence);        
        return newSentence;     
    }
    
    private void tagSentence(Sentence sentenceToTag) {
        tagPhrases(sentenceToTag);
        for (Word word : sentenceToTag.getWords()) {
            tagWord(word);
        }
    }

    private void tagWord(Word wordToTag) {
        tagWordsExactDictionary(wordToTag);
        tagWordsFirstBestNamedEntity(wordToTag);
    }

    private void tagPhrases(Sentence sentenceToTag) {
        tagPhraseExactDictionary(sentenceToTag);
        tagPhraseFirstBestNamedEntity(sentenceToTag);
    }

    private void tagWordsExactDictionary(Word word) {
        for (ExactDictionary exactDictionary : dictionaries) {
            ExactDictionaryChunker dictionaryChunker
                    = new ExactDictionaryChunker(exactDictionary.getDictionary(),
                            IndoEuropeanTokenizerFactory.INSTANCE, false, true);
            Chunking chunking = dictionaryChunker.chunk(word.getWord());
            for (Chunk chunk : chunking.chunkSet()) {
                String type = chunk.type();
                if (chunk.score() == 1.0) {
                    word.addTag(type);
                }
            }
        }
    }

    private void tagWordsFirstBestNamedEntity(Word word) {
        Chunking chunking = nerModelChunker.chunk(word.getWord());
        for (Chunk chunk : chunking.chunkSet()) {
            String type = chunk.type();
            if (chunk.score() == 1.0) {
                word.addTag(type);
            }
        }
    }

    private void tagPhraseExactDictionary(Sentence sentence) {
        for (ExactDictionary exactDictionary : dictionaries) {
            ExactDictionaryChunker dictionaryChunker
                    = new ExactDictionaryChunker(exactDictionary.getDictionary(),
                            IndoEuropeanTokenizerFactory.INSTANCE, false, true);
            Chunking chunking = dictionaryChunker.chunk(sentence.getFullSentence());
            for (Chunk chunk : chunking.chunkSet()) {
                String type = chunk.type();
                String sentenceString = sentence.getFullSentence();
                String match = sentenceString.substring(chunk.start(), chunk.end());
                if (getWordsInString(match) > 1) {
                    sentence.addPhrase(new Phrase(match, type));
                }
            }
        }
    }

    private void tagPhraseFirstBestNamedEntity(Sentence sentence) {
        try {
            Chunking chunking = nerModelChunker.chunk(sentence.getFullSentence());
            for (Chunk chunk : chunking.chunkSet()) {
                String type = chunk.type();
                String sentenceString = sentence.getFullSentence();
                String match = sentenceString.substring(chunk.start(), chunk.end());
                if (getWordsInString(match) > 1) {
                    sentence.addPhrase(new Phrase(match, type));
                }
            }
        } catch (Exception e) {
        }
    }
    
    private int getWordsInString(String testedString) {
        int words = 0;
        String[] wordsInTestedString = testedString.split("\\W+");
        for (String string : wordsInTestedString) {
            words++;
        }
        return words;
    }
    
    

}