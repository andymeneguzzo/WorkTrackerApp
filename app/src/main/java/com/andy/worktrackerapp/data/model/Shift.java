package com.andy.worktrackerapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "shifts")
public class Shift implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String startTime;
    private String endTime;

    private String date;
    private double hoursWorked;
    private double hourlyWage;

    // Costruttore
    public Shift(String date, String startTime, String endTime, double hourlyWage) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public double getHoursWorked() {
        // Supponiamo che gli orari siano nel formato "HH:mm"
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");
        int startHour = Integer.parseInt(start[0]);
        int startMinute = Integer.parseInt(start[1]);
        int endHour = Integer.parseInt(end[0]);
        int endMinute = Integer.parseInt(end[1]);

        double startDecimal = startHour + startMinute / 60.0;
        double endDecimal = endHour + endMinute / 60.0;

        return endDecimal - startDecimal;
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

    // Metodo di calcolo del totale di un singolo turno
    public double getTotalPay() {
        return getHoursWorked() * hourlyWage;
    }
}
