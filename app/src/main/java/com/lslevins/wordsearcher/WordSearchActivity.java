package com.lslevins.wordsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

public class WordSearchActivity extends AppCompatActivity {
//    private String[] grid;
    private Context mContext;
    private GridRecyclerViewAdapter gridAdapter;
    private GridRecyclerViewAdapter listAdapter;
    private RecyclerView wordGrid;
    private RecyclerView wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);
        mContext = getApplicationContext();

        String[] grid = {"A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
                "A", "B","C","D","E","F","G","H","I","J",
        };

        String[] words = {"Swift", "Kotlin", "ObjectiveC", "Variable", "Java", "Mobile"};

        // set up the RecyclerViews
        wordGrid = findViewById(R.id.WordGrid);
        gridAdapter = new GridRecyclerViewAdapter(mContext, grid);
        gridAdapter.setClickListener(new GridRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"You clicked on " + gridAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });

        initRecyclerView(wordGrid,gridAdapter,10);

        wordList = findViewById(R.id.WordList);
        listAdapter = new GridRecyclerViewAdapter(mContext, words);
        listAdapter.setClickListener(new GridRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"You clicked on word" + listAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });

        initRecyclerView(wordList,listAdapter,4);
    }


    /**
     * Initialize the recycler view
     */
    private void initRecyclerView(RecyclerView rv, GridRecyclerViewAdapter a, int cols){
        rv.setAdapter(a);
        rv.setLayoutManager(new GridLayoutManager(this, cols));
//        ItemTouchHelper itemTouchHelper = new
//                ItemTouchHelper(new SwipeToDeleteCallback(adapter));
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
