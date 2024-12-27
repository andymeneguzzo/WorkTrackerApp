package com.andy.worktrackerapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.andy.worktrackerapp.R;

public class MainActivity extends AppCompatActivity {
    private Button btnAddShift, btnViewShifts, btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddShift = findViewById(R.id.btnAddShift);
        btnViewShifts = findViewById(R.id.btnViewShifts);
        btnStats = findViewById(R.id.btnStats);

        btnAddShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddShiftActivity.class);
                startActivity(intent);
            }
        });

        btnViewShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShiftListActivity.class);
                startActivity(intent);
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
    }
}