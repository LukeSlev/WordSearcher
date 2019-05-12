package com.lslevins.wordsearcher;

/**
 * Created by Luke Slevinsky on 2019-05-12.
 */
class Cell {
    char mChar;
    int row,column;
    boolean isSelected;

    public Cell(int row, int col, char c){
        this.row=row;
        this.column=col;
        this.mChar=c;
    }
}
