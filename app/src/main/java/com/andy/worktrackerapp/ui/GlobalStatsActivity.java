//package com.andy.worktrackerapp.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//import android.widget.TextView;
//
//import com.andy.worktrackerapp.R;
//import com.andy.worktrackerapp.data.repository.ShiftRepository;
//import com.google.android.material.button.MaterialButton;
//
//import java.util.Locale;
//
//public class GlobalStatsActivity extends AppCompatActivity {
//
//    private MaterialButton btnShowGlobalStats;
//    private TextView tvGlobalResults;
//
//    private ShiftRepository shiftRepository;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_global_stats);
//
//        // Inizializza i componenti UI
//        btnShowGlobalStats = findViewById(R.id.btnShowGlobalStats);
//        tvGlobalResults = findViewById(R.id.tvGlobalResults);
//
//        shiftRepository = new ShiftRepository(this);
//
//        // Listener per il pulsante mostra statistiche globali
//        btnShowGlobalStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shiftRepository.getGlobalStats(new ShiftRepository.StatsCallback() {
//                    @Override
//                    public void onStatsLoaded(double totalHours, double totalPay) {
//                        String result = String.format(Locale.getDefault(),
//                                "Statistiche Globali:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
//                                totalHours, totalPay);
//                        tvGlobalResults.setText(result);
//                    }
//                });
//            }
//        });
//    }
//}

package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Locale;

public class GlobalStatsActivity extends AppCompatActivity {

    private MaterialButton btnShowGlobalStats;
    private TextView tvGlobalResults;
    private RecyclerView recyclerViewGlobalShifts;
    private ShiftRepository shiftRepository;
    private ShiftAdapter shiftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_stats); // Assicurati che il layout sia corretto

        // Inizializza i componenti UI
        btnShowGlobalStats = findViewById(R.id.btnShowGlobalStats);
        tvGlobalResults = findViewById(R.id.tvGlobalResults);
        recyclerViewGlobalShifts = findViewById(R.id.recyclerViewGlobalShifts);

        // Configura il RecyclerView
        recyclerViewGlobalShifts.setLayoutManager(new LinearLayoutManager(this));
        shiftAdapter = new ShiftAdapter(this, null); // Passeremo la lista successivamente
        recyclerViewGlobalShifts.setAdapter(shiftAdapter);

        shiftRepository = new ShiftRepository(this);

        // Listener per il pulsante mostra statistiche globali
        btnShowGlobalStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ottieni le statistiche globali
                shiftRepository.getGlobalStats(new ShiftRepository.StatsCallback() {
                    @Override
                    public void onStatsLoaded(double totalHours, double totalPay) {
                        String result = String.format(Locale.getDefault(),
                                "Statistiche Globali:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
                                totalHours, totalPay);
                        tvGlobalResults.setText(result);
                    }
                });

                // Ottieni la lista di tutti i turni
                shiftRepository.getAllShifts(new ShiftRepository.ShiftListCallback() {
                    @Override
                    public void onShiftsLoaded(List<Shift> shifts) {
                        shiftAdapter.setShifts(shifts);
                    }
                });
            }
        });
    }
}