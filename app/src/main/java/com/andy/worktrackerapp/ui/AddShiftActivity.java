package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;

public class AddShiftActivity extends AppCompatActivity {
    private EditText etDate, etHours, etWage;
    private Button btnSave;

    private ShiftRepository shiftRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        etDate = findViewById(R.id.etDate);
        etHours = findViewById(R.id.etHours);
        etWage = findViewById(R.id.etWage);
        btnSave = findViewById(R.id.btnSave);

        shiftRepository = new ShiftRepository(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = etDate.getText().toString().trim();
                String hoursStr = etHours.getText().toString().trim();
                String wageStr = etWage.getText().toString().trim();
                
                if(date.isEmpty() || hoursStr.isEmpty() || wageStr.isEmpty()) {
                    Toast.makeText(AddShiftActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
                    return;
                }

                double hours = Double.parseDouble(hoursStr);
                double wage = Double.parseDouble(wageStr);

                Shift newShift = new Shift(date, hours, wage);
                shiftRepository.insertShift(newShift);

                Toast.makeText(AddShiftActivity.this, "Turno salvato con successo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}