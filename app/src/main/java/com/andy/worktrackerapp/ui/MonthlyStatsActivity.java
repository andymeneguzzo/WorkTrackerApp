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
//public class MonthlyStatsActivity extends AppCompatActivity {
//
//    private TextInputEditText etMonth, etYear;
//    private MaterialButton btnShowMonthlyStats;
//    private TextView tvMonthlyResults;
//    private TextInputLayout tilMonth, tilYear;
//
//    private ShiftRepository shiftRepository;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_monthly_stats);
//
//        // Inizializza i componenti UI
//        tilMonth = findViewById(R.id.tilMonth);
//        tilYear = findViewById(R.id.tilYear);
//
//        etMonth = findViewById(R.id.etMonth);
//        etYear = findViewById(R.id.etYear);
//
//        btnShowMonthlyStats = findViewById(R.id.btnShowMonthlyStats);
//        tvMonthlyResults = findViewById(R.id.tvMonthlyResults);
//
//        shiftRepository = new ShiftRepository(this);
//
//        // Listener per il pulsante mostra statistiche mensili
//        btnShowMonthlyStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String month = etMonth.getText().toString().trim();
//                String year = etYear.getText().toString().trim();
//
//                if (month.isEmpty() || year.isEmpty()) {
//                    Toast.makeText(MonthlyStatsActivity.this, "Inserisci mese e anno", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!isValidMonth(month)) {
//                    Toast.makeText(MonthlyStatsActivity.this, "Mese non valido (1-12)", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                shiftRepository.getMonthlyStats(year, padMonth(month), new ShiftRepository.StatsCallback() {
//                    @Override
//                    public void onStatsLoaded(double totalHours, double totalPay) {
//                        String result = String.format(Locale.getDefault(),
//                                "Statistiche Mensili:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
//                                totalHours, totalPay);
//                        tvMonthlyResults.setText(result);
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

public class MonthlyStatsActivity extends AppCompatActivity {

    private TextInputEditText etMonth, etYear;
    private MaterialButton btnShowMonthlyStats;
    private TextView tvMonthlyResults;
    private RecyclerView recyclerViewMonthlyShifts;
    private ShiftRepository shiftRepository;
    private ShiftAdapter shiftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_stats); // Assicurati che il layout sia corretto

        // Inizializza i componenti UI
        etMonth = findViewById(R.id.etMonth);
        etYear = findViewById(R.id.etYear);
        btnShowMonthlyStats = findViewById(R.id.btnShowMonthlyStats);
        tvMonthlyResults = findViewById(R.id.tvMonthlyResults);
        recyclerViewMonthlyShifts = findViewById(R.id.recyclerViewMonthlyShifts);

        // Configura il RecyclerView
        recyclerViewMonthlyShifts.setLayoutManager(new LinearLayoutManager(this));
        shiftAdapter = new ShiftAdapter(this, null); // Passeremo la lista successivamente
        recyclerViewMonthlyShifts.setAdapter(shiftAdapter);

        shiftRepository = new ShiftRepository(this);

        // Listener per il pulsante mostra statistiche mensili
        btnShowMonthlyStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month = etMonth.getText().toString().trim();
                String year = etYear.getText().toString().trim();

                if (month.isEmpty() || year.isEmpty()) {
                    Toast.makeText(MonthlyStatsActivity.this, "Inserisci mese e anno", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidMonth(month)) {
                    Toast.makeText(MonthlyStatsActivity.this, "Mese non valido (1-12)", Toast.LENGTH_SHORT).show();
                    return;
                }

                String paddedMonth = padMonth(month);

                // Ottieni le statistiche mensili
                shiftRepository.getMonthlyStats(year, paddedMonth, new ShiftRepository.StatsCallback() {
                    @Override
                    public void onStatsLoaded(double totalHours, double totalPay) {
                        String result = String.format(Locale.getDefault(),
                                "Statistiche Mensili:\nOre Totali: %.2f\nTotale Pagato: %.2f €",
                                totalHours, totalPay);
                        tvMonthlyResults.setText(result);
                    }
                });

                // Ottieni la lista dei turni mensili
                shiftRepository.getShiftsByMonthAndYear(year, paddedMonth, new ShiftRepository.ShiftListCallback() {
                    @Override
                    public void onShiftsLoaded(List<Shift> shifts) {
                        shiftAdapter.setShifts(shifts);
                    }
                });
            }
        });
    }

    // Metodo per validare il mese
    private boolean isValidMonth(String month) {
        try {
            int m = Integer.parseInt(month);
            return m >= 1 && m <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Metodo per aggiungere uno zero davanti al mese se necessario
    private String padMonth(String month) {
        if (month.length() == 1) {
            return "0" + month;
        }
        return month;
    }
}