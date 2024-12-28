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
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.android.material.textfield.TextInputLayout;
//
//import java.util.Locale;
//
//public class AnnualStatsActivity extends AppCompatActivity {
//
//    private TextInputEditText etYear;
//    private MaterialButton btnShowAnnualStats;
//    private TextView tvAnnualResults;
//    private TextInputLayout tilYear;
//
//    private ShiftRepository shiftRepository;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_annual_stats);
//
//        // Inizializza i componenti UI
//        tilYear = findViewById(R.id.tilYear);
//        etYear = findViewById(R.id.etYear);
//
//        btnShowAnnualStats = findViewById(R.id.btnShowAnnualStats);
//        tvAnnualResults = findViewById(R.id.tvAnnualResults);
//
//        shiftRepository = new ShiftRepository(this);
//
//        // Listener per il pulsante mostra statistiche annuali
//        btnShowAnnualStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String year = etYear.getText().toString().trim();
//
//                if (year.isEmpty()) {
//                    Toast.makeText(AnnualStatsActivity.this, "Inserisci l'anno", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                shiftRepository.getYearlyStats(year, new ShiftRepository.StatsCallback() {
//                    @Override
//                    public void onStatsLoaded(double totalHours, double totalPay) {
//                        String result = String.format(Locale.getDefault(),
//                                "Statistiche Annuali:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
//                                totalHours, totalPay);
//                        tvAnnualResults.setText(result);
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

public class AnnualStatsActivity extends AppCompatActivity {

    private TextInputEditText etYear;
    private MaterialButton btnShowAnnualStats;
    private TextView tvAnnualResults;
    private RecyclerView recyclerViewAnnualShifts;
    private ShiftRepository shiftRepository;
    private ShiftAdapter shiftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_stats); // Assicurati che il layout sia corretto

        // Inizializza i componenti UI
        etYear = findViewById(R.id.etYear);
        btnShowAnnualStats = findViewById(R.id.btnShowAnnualStats);
        tvAnnualResults = findViewById(R.id.tvAnnualResults);
        recyclerViewAnnualShifts = findViewById(R.id.recyclerViewAnnualShifts);

        // Configura il RecyclerView
        recyclerViewAnnualShifts.setLayoutManager(new LinearLayoutManager(this));
        shiftAdapter = new ShiftAdapter(this, null); // Passeremo la lista successivamente
        recyclerViewAnnualShifts.setAdapter(shiftAdapter);

        shiftRepository = new ShiftRepository(this);

        // Listener per il pulsante mostra statistiche annuali
        btnShowAnnualStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String year = etYear.getText().toString().trim();

                if (year.isEmpty()) {
                    Toast.makeText(AnnualStatsActivity.this, "Inserisci l'anno", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ottieni le statistiche annuali
                shiftRepository.getYearlyStats(year, new ShiftRepository.StatsCallback() {
                    @Override
                    public void onStatsLoaded(double totalHours, double totalPay) {
                        String result = String.format(Locale.getDefault(),
                                "Statistiche Annuali:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
                                totalHours, totalPay);
                        tvAnnualResults.setText(result);
                    }
                });

                // Ottieni la lista dei turni annuali
                shiftRepository.getShiftsByYear(year, new ShiftRepository.ShiftListCallback() {
                    @Override
                    public void onShiftsLoaded(List<Shift> shifts) {
                        shiftAdapter.setShifts(shifts);
                    }
                });
            }
        });
    }
}