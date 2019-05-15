package com.lslevins.wordsearcher;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke Slevinsky on 2019-05-14.
 */
public class GameBoard {
    final public static String TAG = "GameBoard";
    private static final GameBoard ourInstance = new GameBoard();
    private List<String> words = new ArrayList<String>();
    private StringBuilder selectedChars = new StringBuilder();
    private boolean[] selectedCells;

    int lastIdx;
    int score = 0;
    int maxScore;

    public static GameBoard getInstance() {
        return ourInstance;
    }

    private GameBoard() {
    }

    public int getLastIdx() {
        return lastIdx;
    }

    public void setLastIdx(int lastIdx) {
        this.lastIdx = lastIdx;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int incrScore(){
        return ++this.score;
    }

    public void setSelectedCells(boolean[] selectedCells) {
        this.selectedCells = selectedCells;
    }

    public boolean[] getSelectedCells() {
        return this.selectedCells;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
        setMaxScore(words.size());
    }

    public StringBuilder getSelectedChars() {
        return selectedChars;
    }

    public void setSelectedChars(StringBuilder selected) {
        this.selectedChars = selected;
    }

    public int getSelectedLength(){
        return getSelectedChars().length();
    }

    public void addChar(char c) {
        getSelectedChars().append(c);
    }

    public void setMaxScore(int i) {
        this.maxScore = i;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public void clearSelectedChars(){
        getSelectedChars().setLength(0);
    }


    public String validWord(){
        String forward = getSelectedChars().toString();
        String backward = getSelectedChars().reverse().toString();

        String ret = "";
        for (String s : getWords()) {
            if (s.toLowerCase().compareTo(forward) == 0) {
                ret = forward;
            } else if (s.toLowerCase().compareTo(backward) == 0) {
                ret = backward;
            }
        }
        return ret;
    }

    public void resetSelections(){
        boolean[] selected = getSelectedCells();
        for (int i=0;i<selected.length;i++){
            selected[i] = false;
        }
        clearSelectedChars();
    }
}
