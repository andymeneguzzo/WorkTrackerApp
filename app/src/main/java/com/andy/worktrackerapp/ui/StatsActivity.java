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
//public class StatsActivity extends AppCompatActivity {
//
//    private TextInputEditText etMonth, etYear;
//    private MaterialButton btnMonthlyStats, btnYearlyStats, btnTotalStats;
//    private TextView tvResults;
//    private TextInputLayout tilMonth, tilYear;
//
//    private ShiftRepository shiftRepository;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stats);
//
//        // Inizializza i componenti UI
//        tilMonth = findViewById(R.id.tilMonth);
//        tilYear = findViewById(R.id.tilYear);
//
//        etMonth = findViewById(R.id.etMonth);
//        etYear = findViewById(R.id.etYear);
//
//        btnMonthlyStats = findViewById(R.id.btnMonthlyStats);
//        btnYearlyStats = findViewById(R.id.btnYearlyStats);
//        btnTotalStats = findViewById(R.id.btnTotalStats);
//
//        tvResults = findViewById(R.id.tvResults);
//
//        shiftRepository = new ShiftRepository(this);
//
//        // Listener per il pulsante Statistiche Mensili
//        btnMonthlyStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String month = etMonth.getText().toString().trim();
//                String year = etYear.getText().toString().trim();
//
//                if (month.isEmpty() || year.isEmpty()) {
//                    Toast.makeText(StatsActivity.this, "Inserisci mese e anno", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!isValidMonth(month)) {
//                    Toast.makeText(StatsActivity.this, "Mese non valido (1-12)", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                shiftRepository.getMonthlyStats(year, padMonth(month), new ShiftRepository.StatsCallback() {
//                    @Override
//                    public void onStatsLoaded(double totalHours, double totalPay) {
//                        String result = String.format(Locale.getDefault(),
//                                "Statistiche Mensili:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
//                                totalHours, totalPay);
//                        tvResults.setText(result);
//                    }
//                });
//            }
//        });
//
//        // Listener per il pulsante Statistiche Annuali
//        btnYearlyStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String year = etYear.getText().toString().trim();
//
//                if (year.isEmpty()) {
//                    Toast.makeText(StatsActivity.this, "Inserisci l'anno", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                shiftRepository.getYearlyStats(year, new ShiftRepository.StatsCallback() {
//                    @Override
//                    public void onStatsLoaded(double totalHours, double totalPay) {
//                        String result = String.format(Locale.getDefault(),
//                                "Statistiche Annuali:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
//                                totalHours, totalPay);
//                        tvResults.setText(result);
//                    }
//                });
//            }
//        });
//
//        // Listener per il pulsante Statistiche Totali
//        btnTotalStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shiftRepository.getGlobalStats(new ShiftRepository.StatsCallback() {
//                    @Override
//                    public void onStatsLoaded(double totalHours, double totalPay) {
//                        String result = String.format(Locale.getDefault(),
//                                "Statistiche Totali:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
//                                totalHours, totalPay);
//                        tvResults.setText(result);
//                    }
//                });
//            }
//        });
//    }
//
//    // Metodo per validare il mese
//    private boolean isValidMonth(String month) {
//        try {
//            int m = Integer.parseInt(month);
//            return m >= 1 && m <= 12;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    // Metodo per aggiungere uno zero davanti al mese se necessario
//    private String padMonth(String month) {
//        if (month.length() == 1) {
//            return "0" + month;
//        }
//        return month;
//    }
//}

package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andy.worktrackerapp.R;
import com.google.android.material.button.MaterialButton;

public class StatsActivity extends AppCompatActivity {

    private MaterialButton btnMonthlyStats, btnAnnualStats, btnGlobalStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats); // Assicurati che il layout sia corretto

        // Inizializza i pulsanti
        btnMonthlyStats = findViewById(R.id.btnMonthlyStats);
        btnAnnualStats = findViewById(R.id.btnAnnualStats);
        btnGlobalStats = findViewById(R.id.btnGlobalStats);

        // Listener per Statistiche Mensili
        btnMonthlyStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsActivity.this, MonthlyStatsActivity.class);
                startActivity(intent);
            }
        });

        // Listener per Statistiche Annuali
        btnAnnualStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsActivity.this, AnnualStatsActivity.class);
                startActivity(intent);
            }
        });

        // Listener per Statistiche Globali
        btnGlobalStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsActivity.this, GlobalStatsActivity.class);
                startActivity(intent);
            }
        });
    }
}