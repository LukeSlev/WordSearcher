package com.lslevins.wordsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WordSearchActivity extends AppCompatActivity {
    private Cell[][] mTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);

        mTable = new Cell[10][10];
    }
}
