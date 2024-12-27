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

public class EditShiftActivity extends AppCompatActivity {
    private EditText etDate, etHours, etWage;
    private Button btnUpdate;

    private ShiftRepository shiftRepository;
    private Shift shiftToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shift);

        etDate = findViewById(R.id.etDate);
        etHours = findViewById(R.id.etHours);
        etWage = findViewById(R.id.etWage);
        btnUpdate = findViewById(R.id.btnUpdate);

        shiftRepository = new ShiftRepository(this);

        // Recupera oggetto Shift passato tramite Intent
        if(getIntent() != null && getIntent().hasExtra("shift")) {
            shiftToEdit = (Shift) getIntent().getSerializableExtra("shift");
            if(shiftToEdit != null) {
                etDate.setText(shiftToEdit.getDate());
                etHours.setText(String.valueOf(shiftToEdit.getHoursWorked()));
                etWage.setText(String.valueOf(shiftToEdit.getHourlyWage()));
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString().trim();
                String hoursStr = etHours.getText().toString().trim();
                String wageStr = etWage.getText().toString().trim();

                if(date.isEmpty() || hoursStr.isEmpty() || wageStr.isEmpty()) {
                    Toast.makeText(EditShiftActivity.this, "Compila tutti i campi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double hours = Double.parseDouble(hoursStr);
                double wage = Double.parseDouble(wageStr);

                shiftToEdit.setDate(date);
                shiftToEdit.setHoursWorked(hours);
                shiftToEdit.setHourlyWage(wage);

                shiftRepository.updateShift(shiftToEdit);

                Toast.makeText(EditShiftActivity.this, "Turno aggiornato con successo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
