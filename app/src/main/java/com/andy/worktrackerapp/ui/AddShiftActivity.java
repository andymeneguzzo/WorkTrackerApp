package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddShiftActivity extends AppCompatActivity {

    private TextInputEditText etDate, etStartTime, etEndTime, etWage;
    private MaterialButton btnSave;
    private TextInputLayout tilDate, tilStartTime, tilEndTime, tilWage;

    private ShiftRepository shiftRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        // Inizializza i componenti UI
        tilDate = findViewById(R.id.tilDate);
        tilStartTime = findViewById(R.id.tilStartTime);
        tilEndTime = findViewById(R.id.tilEndTime);
        tilWage = findViewById(R.id.tilWage);

        etDate = findViewById(R.id.etDate);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        etWage = findViewById(R.id.etWage);
        btnSave = findViewById(R.id.btnSave);

        shiftRepository = new ShiftRepository(this);

        // Imposta listener per il campo data per aprire un DatePicker
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        // Listener per selezionare l'orario di inizio
        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(true);
            }
        });

        // Listener per selezionare l'orario di fine
        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(false);
            }
        });

        // Listener per il pulsante salva
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveShift();
            }
        });
    }

    private void showDatePicker() {
        // Implementazione di un DatePicker personalizzato
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day  = calendar.get(Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(
                AddShiftActivity.this,
                new android.app.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Formattazione della data in dd-MM-yyyy
                        String formattedDate = String.format(Locale.getDefault(), "%02d-%02d-%04d", selectedDay, selectedMonth + 1, selectedYear);
                        etDate.setText(formattedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void showTimePicker(final boolean isStartTime) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                AddShiftActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int selectedHour, int selectedMinute) {
                        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        if (isStartTime) {
                            etStartTime.setText(formattedTime);
                        } else {
                            etEndTime.setText(formattedTime);
                        }
                    }
                },
                hour, minute, true
        );
        timePickerDialog.show();
    }

    private void saveShift() {
        String date = etDate.getText().toString().trim();
        String startTime = etStartTime.getText().toString().trim();
        String endTime = etEndTime.getText().toString().trim();
        String wageStr = etWage.getText().toString().trim();

        if (date.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || wageStr.isEmpty()) {
            Toast.makeText(AddShiftActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        double wage;
        try {
            wage = Double.parseDouble(wageStr);
        } catch (NumberFormatException e) {
            Toast.makeText(AddShiftActivity.this, "Paga oraria non valida", Toast.LENGTH_SHORT).show();
            return;
        }

        Shift newShift = new Shift(date, startTime, endTime, wage);
        shiftRepository.insertShift(newShift);

        Toast.makeText(AddShiftActivity.this, "Turno salvato con successo!", Toast.LENGTH_SHORT).show();
        finish();
    }
}