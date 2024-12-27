package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;

public class ShiftListActivity extends AppCompatActivity {
    private ListView listViewShifts;
    private ShiftRepository shiftRepository;

    private List<Shift> allShifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_list);

        listViewShifts = findViewById(R.id.listViewShifts);
        shiftRepository = new ShiftRepository(this);

        loadShifts();

        listViewShifts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Shift selectedShift = allShifts.get(position);
                Intent intent = new Intent(ShiftListActivity.this, EditShiftActivity.class);
                intent.putExtra("shift", selectedShift); // Passa l'oggetto Shift
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadShifts();
    }

    private void loadShifts() {
        allShifts = shiftRepository.getAllShifts();

        List<String> displayList = new ArrayList<>();
        for (Shift shift : allShifts) {
            String item = "Data: " + shift.getDate() +
                    "\nOre: " + shift.getHoursWorked() +
                    "\nPaga oraria: " + shift.getHourlyWage() +
                    "\nTotale turno: " + shift.getTotalPay() + " €";
            displayList.add(item);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );
        listViewShifts.setAdapter(adapter);
    }
}
