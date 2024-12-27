package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_list);

        listViewShifts = findViewById(R.id.listViewShifts);
        shiftRepository = new ShiftRepository(this);

        List<Shift> allShifts = shiftRepository.getAllShifts();

        List<String> displayList = new ArrayList<>();
        for(Shift shift : allShifts) {
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
