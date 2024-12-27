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

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    // Calcolo totale dello stipendio
    public double getTotalPay() {
        return hoursWorked * hourlyWage;
    }
}
