package com.lslevins.wordsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class EndGame extends AppCompatActivity {
    final public static String TAG = "EndGame";
    private TextView endTime;
    private MaterialButton restartButton;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        restartButton = findViewById(R.id.RestartButton);
        endTime = findViewById(R.id.EndTime);
        mContext = getApplicationContext();

        String time = getIntent().getStringExtra(WordSearchActivity.FINISH_TIME);
        endTime.setText(time);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
