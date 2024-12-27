package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;

public class StatsActivity extends AppCompatActivity {
    private EditText etMonth, etYear;
    private Button btnMonthlyStats, btnYearStats, btnTotalStats;
    private TextView tvResults;

    private ShiftRepository shiftRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        etMonth = findViewById(R.id.etMonth);
        etYear = findViewById(R.id.etYear);
        btnMonthlyStats = findViewById(R.id.btnMonthlyStats);
        btnYearStats = findViewById(R.id.btnYearlyStats);
        btnTotalStats = findViewById(R.id.btnTotalStats);
        tvResults = findViewById(R.id.tvResults);

        shiftRepository = new ShiftRepository(this);

        // Statistiche mensili
        // Statistiche annuali
        // Statistiche totali

        // TODO da fare i metodi sopra
    }
}
