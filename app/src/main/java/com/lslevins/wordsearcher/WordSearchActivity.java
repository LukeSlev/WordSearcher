package com.lslevins.wordsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class WordSearchActivity extends AppCompatActivity {
//    private String[] grid;
    private Context mContext;
    private WordListRecyclerViewAdapter listAdapter;
    private GridRecyclerViewAdapter gridAdapter;
    private RecyclerView wordGrid;
    private RecyclerView wordList;
    private TextView timerView;
    private TextView scoreView;

    private int score = 0;

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    private Timer timer = new Timer();
    private final int REFRESH_RATE = 100;

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    timerView.setText(String.format("%02d:%02d",timer.getElapsedTimeMin(),timer.getElapsedTimeSecs()%60));
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    timerView.setText(String.format("%02d:%02d",timer.getElapsedTimeMin(),timer.getElapsedTimeSecs()%60));
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);
        timerView = findViewById(R.id.timeLabel);
        scoreView = findViewById(R.id.scoreLabel);
        mContext = getApplicationContext();

        char[] grid = {
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j',
                'a','b','c','d','e','f','g','h','i','j'
        };


        List<String> words = Arrays.asList("Swift", "Kotlin", "ObjectiveC", "Variable", "Java", "Mobile");

        scoreView.setText(String.format("%d/%d",score,words.size()));
        mHandler.sendEmptyMessage(MSG_START_TIMER);
        GridGenerator g = new GridGenerator();

        Toast.makeText(mContext,String.format("we used %d",g.setGrid(words,grid).size()),Toast.LENGTH_SHORT).show();

        // set up the RecyclerViews
        wordGrid = findViewById(R.id.WordGrid);
        gridAdapter = new GridRecyclerViewAdapter(mContext, grid);
        gridAdapter.setClickListener(new GridRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"You clicked on " + gridAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });

        wordGrid.setAdapter(gridAdapter);
        wordGrid.setLayoutManager(new GridLayoutManager(mContext, 10));

        wordList = findViewById(R.id.WordList);
        listAdapter = new WordListRecyclerViewAdapter(mContext,(String[])words.toArray());
        listAdapter.setClickListener(new WordListRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"You clicked on " + listAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });

        wordList.setAdapter(listAdapter);
        wordList.setLayoutManager(new GridLayoutManager(mContext, 4));
    }

}
