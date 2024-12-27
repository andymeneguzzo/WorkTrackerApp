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
    private Button btnMonthlyStats, btnYearlyStats, btnTotalStats;
    private TextView tvResults;

    private ShiftRepository shiftRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        etMonth = findViewById(R.id.etMonth);
        etYear = findViewById(R.id.etYear);
        btnMonthlyStats = findViewById(R.id.btnMonthlyStats);
        btnYearlyStats = findViewById(R.id.btnYearlyStats);
        btnTotalStats = findViewById(R.id.btnTotalStats);
        tvResults = findViewById(R.id.tvResults);

        shiftRepository = new ShiftRepository(this);

        // Statistiche mensili
        btnMonthlyStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String month = etMonth.getText().toString();
                String year = etYear.getText().toString();

                // validazione dell'input
                if (month.isEmpty() || year.isEmpty()) {
                    Toast.makeText(StatsActivity.this, "Campi mese e anno vuoti, completali", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (month.length() == 1) {
                    month = "0" + month;
                }

                List<Shift> shifts = shiftRepository.getShiftsByMonthAndYear(year, month);

                double totalHours = 0;
                double totalPay = 0;

                for (Shift shift : shifts) {
                    totalHours += shift.getHoursWorked();
                    totalPay += shift.getTotalPay();
                }

                String result = "Mese: " + month + "/" + year +
                        "\nOre totali: " + totalHours +
                        "\nPaga totale: " + totalPay + " €";
                tvResults.setText(result);
            }
        });

        // Statistiche annuali
        btnYearlyStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String year = etYear.getText().toString().trim();
                if (year.isEmpty()) {
                    Toast.makeText(StatsActivity.this, "Inserire anno", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Shift> yearlyShifts = shiftRepository.getShiftsByYear(year);

                double totalHours = 0;
                double totalPay = 0;
                for (Shift shift : yearlyShifts) {
                    totalHours += shift.getHoursWorked();
                    totalPay += shift.getTotalPay();
                }

                String result = "Anno: " + year +
                        "\nOre totali: " + totalHours +
                        "\nPaga totale: " + totalPay + " €";
                tvResults.setText(result);
            }
        });

        // Statistiche totali
        btnTotalStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double totalHours = shiftRepository.getTotalHours();
                double totalPay = shiftRepository.getTotalPay();

                String result = "Ore totali registrate: " + totalHours +
                        "\nPaga totale: " + totalPay + " €";
                tvResults.setText(result);
            }
        });
    }
}
