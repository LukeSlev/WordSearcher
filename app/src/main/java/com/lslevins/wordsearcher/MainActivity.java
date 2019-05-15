package com.lslevins.wordsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    final public static String TAG = "MainActivity";
    private MaterialButton startButton;
    private MaterialButton leaderBoardButton;
    private ConstraintLayout mainLayout;
    private Context mContext;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=0;

        startButton = findViewById(R.id.StartButton);
        leaderBoardButton = findViewById(R.id.LeaderboardButton);
        mainLayout = findViewById(R.id.MainLayout);
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
                Animation shake = AnimationUtils.loadAnimation(mContext,R.anim.shake);
                view.startAnimation(shake);
                if (i%2 ==0){
                    mainLayout.setBackground(getResources().getDrawable(R.drawable.opposite_grey_gradient));
                } else{
                    mainLayout.setBackground(getResources().getDrawable(R.drawable.grey_gradient));
                }
                i++;
            }
        });

    }
}
