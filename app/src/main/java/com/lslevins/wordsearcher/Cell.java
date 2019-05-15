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
        this.isSelected=false;
    }


    public char getmChar() {
        return mChar;
    }

    public void setmChar(char mChar) {
        this.mChar = mChar;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
