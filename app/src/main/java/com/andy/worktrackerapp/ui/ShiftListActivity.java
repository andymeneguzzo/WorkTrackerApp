//package com.andy.worktrackerapp.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import com.andy.worktrackerapp.R;
//import com.andy.worktrackerapp.data.model.Shift;
//import com.andy.worktrackerapp.data.repository.ShiftRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShiftListActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerViewShifts;
//    private ShiftRepository shiftRepository;
//    private ShiftAdapter shiftAdapter;
//    private List<Shift> allShifts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shift_list);
//
//        recyclerViewShifts = findViewById(R.id.recyclerViewShifts);
//        recyclerViewShifts.setLayoutManager(new LinearLayoutManager(this));
//
//        shiftRepository = new ShiftRepository(this);
//        allShifts = new ArrayList<>();
//
//        shiftAdapter = new ShiftAdapter(this, allShifts);
//        recyclerViewShifts.setAdapter(shiftAdapter);
//
//        loadShifts();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadShifts(); // Ricarica i turni dopo un aggiornamento
//    }
//
//    private void loadShifts() {
//        allShifts = shiftRepository.getAllShifts();
//        shiftAdapter.setShifts(allShifts);
//    }
//}

package com.andy.worktrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;

import java.util.ArrayList;
import java.util.List;

public class ShiftListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewShifts;
    private ShiftRepository shiftRepository;
    private ShiftAdapter shiftAdapter;
    private List<Shift> allShifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_list);

        recyclerViewShifts = findViewById(R.id.recyclerViewShifts);
        recyclerViewShifts.setLayoutManager(new LinearLayoutManager(this));

        shiftRepository = new ShiftRepository(this);
        allShifts = new ArrayList<>();

        shiftAdapter = new ShiftAdapter(this, allShifts);
        recyclerViewShifts.setAdapter(shiftAdapter);

        loadShifts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadShifts(); // Ricarica i turni dopo un aggiornamento
    }

    private void loadShifts() {
        shiftRepository.getAllShifts(new ShiftRepository.ShiftListCallback() {
            @Override
            public void onShiftsLoaded(List<Shift> shifts) {
                allShifts = shifts;
                shiftAdapter.setShifts(allShifts);
            }
        });
    }
}