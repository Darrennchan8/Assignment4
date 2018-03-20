package com.example.darren.assignment4;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Created by Darren on 3/17/2018.
 */

@Entity(primaryKeys = { "firstWord", "secondWord" })
public class SynonymPair {
    @NonNull
    private String firstWord;

    @NonNull
    private String secondWord;

    public SynonymPair(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }
    public SynonymPair() {
        this("", "");
    }

    public String getFirstWord() {
        return this.firstWord;
    }
    public void setFirstWord(String word) {
        this.firstWord = word;
    }
    public String getSecondWord() {
        return this.secondWord;
    }
    public void setSecondWord(String word) {
        this.secondWord = word;
    }
    public String getSynonym(String word) {
        return firstWord.equalsIgnoreCase(word) ? secondWord : (secondWord.equalsIgnoreCase(word) ? firstWord : null);
    }
    public String toString() {
        return firstWord + ": " + secondWord;
    }
    public boolean equals(Object obj) {
        return this == obj || obj instanceof String && firstWord.equalsIgnoreCase(obj.toString()) | secondWord.equalsIgnoreCase(obj.toString());
    }
}
