//package com.andy.worktrackerapp.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import com.andy.worktrackerapp.R;
//import com.andy.worktrackerapp.data.model.Shift;
//import com.andy.worktrackerapp.data.repository.ShiftRepository;
//import com.google.android.material.button.MaterialButton;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.android.material.textfield.TextInputLayout;
//
//import java.util.Calendar;
//import java.util.Locale;
//
//public class EditShiftActivity extends AppCompatActivity {
//
//    private TextInputEditText etDate, etStartTime, etEndTime, etWage;
//    private MaterialButton btnUpdate;
//    private TextInputLayout tilDate, tilStartTime, tilEndTime, tilWage;
//
//    private ShiftRepository shiftRepository;
//    private Shift shiftToEdit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_shift);
//
//        // Inizializza i componenti UI
//        tilDate = findViewById(R.id.tilDate);
//        tilStartTime = findViewById(R.id.tilStartTime);
//        tilEndTime = findViewById(R.id.tilEndTime);
//        tilWage = findViewById(R.id.tilWage);
//
//        etDate = findViewById(R.id.etDate);
//        etStartTime = findViewById(R.id.etStartTime);
//        etEndTime = findViewById(R.id.etEndTime);
//        etWage = findViewById(R.id.etWage);
//        btnUpdate = findViewById(R.id.btnUpdate);
//
//        shiftRepository = new ShiftRepository(this);
//
//        // Recupera l'oggetto Shift passato tramite Intent
//        if (getIntent() != null && getIntent().hasExtra("shift")) {
//            shiftToEdit = (Shift) getIntent().getSerializableExtra("shift");
//            if (shiftToEdit != null) {
//                etDate.setText(shiftToEdit.getDate());
//                etStartTime.setText(shiftToEdit.getStartTime());
//                etEndTime.setText(shiftToEdit.getEndTime());
//                etWage.setText(String.valueOf(shiftToEdit.getHourlyWage()));
//            }
//        }
//
//        // Imposta listener per il campo data per aprire un DatePicker
//        etDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDatePicker();
//            }
//        });
//
//        // Listener per selezionare l'orario di inizio
//        etStartTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTimePicker(true);
//            }
//        });
//
//        // Listener per selezionare l'orario di fine
//        etEndTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTimePicker(false);
//            }
//        });
//
//        // Listener per il pulsante aggiorna
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateShift();
//            }
//        });
//    }
//
//    private void showDatePicker() {
//        final Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day  = calendar.get(Calendar.DAY_OF_MONTH);
//
//        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(
//                EditShiftActivity.this,
//                new android.app.DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(android.widget.DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
//                        String formattedDate = String.format(Locale.getDefault(), "%02d-%02d-%04d", selectedDay, selectedMonth + 1, selectedYear);
//                        etDate.setText(formattedDate);
//                    }
//                },
//                year, month, day
//        );
//        datePickerDialog.show();
//    }
//
//    private void showTimePicker(final boolean isStartTime) {
//        final Calendar calendar = Calendar.getInstance();
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//
//        TimePickerDialog timePickerDialog = new TimePickerDialog(
//                EditShiftActivity.this,
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(android.widget.TimePicker view, int selectedHour, int selectedMinute) {
//                        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
//                        if (isStartTime) {
//                            etStartTime.setText(formattedTime);
//                        } else {
//                            etEndTime.setText(formattedTime);
//                        }
//                    }
//                },
//                hour, minute, true
//        );
//        timePickerDialog.show();
//    }
//
//    private void updateShift() {
//        String date = etDate.getText().toString().trim();
//        String startTime = etStartTime.getText().toString().trim();
//        String endTime = etEndTime.getText().toString().trim();
//        String wageStr = etWage.getText().toString().trim();
//
//        if (date.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || wageStr.isEmpty()) {
//            Toast.makeText(EditShiftActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        double wage;
//        try {
//            wage = Double.parseDouble(wageStr);
//        } catch (NumberFormatException e) {
//            Toast.makeText(EditShiftActivity.this, "Paga oraria non valida", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Aggiorna l'oggetto Shift
//        shiftToEdit.setDate(date);
//        shiftToEdit.setStartTime(startTime);
//        shiftToEdit.setEndTime(endTime);
//        shiftToEdit.setHourlyWage(wage);
//
//        // Aggiorna il database
//        shiftRepository.updateShift(shiftToEdit);
//
//        Toast.makeText(EditShiftActivity.this, "Turno aggiornato con successo!", Toast.LENGTH_SHORT).show();
//        finish(); // Chiudi l'attività e torna alla lista dei turni
//    }
//}

package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

public class EditShiftActivity extends AppCompatActivity {

    private TextInputEditText etDate, etStartTime, etEndTime, etWage;
    private MaterialButton btnUpdate;
    private TextInputLayout tilDate, tilStartTime, tilEndTime, tilWage;

    private ShiftRepository shiftRepository;
    private Shift shiftToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shift);

        // Inizializza i componenti UI
        tilDate = findViewById(R.id.tilDate);
        tilStartTime = findViewById(R.id.tilStartTime);
        tilEndTime = findViewById(R.id.tilEndTime);
        tilWage = findViewById(R.id.tilWage);

        etDate = findViewById(R.id.etDate);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        etWage = findViewById(R.id.etWage);
        btnUpdate = findViewById(R.id.btnUpdate);

        shiftRepository = new ShiftRepository(this);

        // Recupera l'oggetto Shift passato tramite Intent
        if (getIntent() != null && getIntent().hasExtra("shift")) {
            shiftToEdit = (Shift) getIntent().getSerializableExtra("shift");
            if (shiftToEdit != null) {
                etDate.setText(shiftToEdit.getDate());
                etStartTime.setText(shiftToEdit.getStartTime());
                etEndTime.setText(shiftToEdit.getEndTime());
                etWage.setText(String.valueOf(shiftToEdit.getHourlyWage()));
            }
        }

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

        // Listener per il pulsante aggiorna
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateShift();
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day  = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                EditShiftActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Formattazione della data in dd-MM-yyyy
                    String formattedDate = String.format(Locale.getDefault(), "%02d-%02d-%04d", selectedDay, selectedMonth + 1, selectedYear);
                    etDate.setText(formattedDate);
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
                EditShiftActivity.this,
                (view, selectedHour, selectedMinute) -> {
                    String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                    if (isStartTime) {
                        etStartTime.setText(formattedTime);
                    } else {
                        etEndTime.setText(formattedTime);
                    }
                },
                hour, minute, true
        );
        timePickerDialog.show();
    }

    private void updateShift() {
        String date = etDate.getText().toString().trim();
        String startTime = etStartTime.getText().toString().trim();
        String endTime = etEndTime.getText().toString().trim();
        String wageStr = etWage.getText().toString().trim();

        if (date.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || wageStr.isEmpty()) {
            Toast.makeText(EditShiftActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        double wage;
        try {
            wage = Double.parseDouble(wageStr);
        } catch (NumberFormatException e) {
            Toast.makeText(EditShiftActivity.this, "Paga oraria non valida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validazione orari
        if (!isEndTimeAfterStartTime(startTime, endTime)) {
            Toast.makeText(EditShiftActivity.this, "L'orario di fine deve essere dopo quello di inizio", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aggiorna l'oggetto Shift
        shiftToEdit.setDate(date);
        shiftToEdit.setStartTime(startTime);
        shiftToEdit.setEndTime(endTime);
        shiftToEdit.setHourlyWage(wage);

        // Aggiorna il database
        shiftRepository.updateShift(shiftToEdit);

        Toast.makeText(EditShiftActivity.this, "Turno aggiornato con successo!", Toast.LENGTH_SHORT).show();
        finish(); // Chiudi l'attività e torna alla lista dei turni
    }

    private boolean isEndTimeAfterStartTime(String start, String end) {
        String[] startParts = start.split(":");
        String[] endParts = end.split(":");
        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);

        if (endHour > startHour) {
            return true;
        } else if (endHour == startHour && endMinute > startMinute) {
            return true;
        } else {
            return false;
        }
    }
}