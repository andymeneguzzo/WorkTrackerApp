package com.andy.worktrackerapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shifts")
public class Shift {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private double hoursWorked;
    private double hourlyWage;

    // Costruttore
    public Shift(String date, double hoursWorked, double hourlyWage) {
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
    }
}
