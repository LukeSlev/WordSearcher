package com.lslevins.wordsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    final public static String TAG = "MainActivity";
    MaterialButton startButton;
    MaterialButton leaderBoardButton;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.StartButton);
        leaderBoardButton = findViewById(R.id.LeaderboardButton);
        mContext = getApplicationContext();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WordSearchActivity.class);
                startActivity(intent);
            }
        });

        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"YOU WIN",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
