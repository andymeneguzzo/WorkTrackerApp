package com.andy.worktrackerapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "shifts")
public class Shift implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;             // Data del turno (es. "27-12-2024")
    private String startTime;       // Orario di inizio (es. "09:00")
    private String endTime;         // Orario di fine (es. "17:00")
    private double hourlyWage;      // Paga oraria

    // Costruttore
    public Shift(String date, String startTime, String endTime, double hourlyWage) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hourlyWage = hourlyWage;
    }

    protected Shift(Parcel in) {
        id = in.readInt();
        date = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        hourlyWage = in.readDouble();
    }

    // Only for testing purpose
    public Shift() {}

    public static final Creator<Shift> CREATOR = new Creator<Shift>() {
        @Override
        public Shift createFromParcel(Parcel in) {
            return new Shift(in);
        }

        @Override
        public Shift[] newArray(int size) {
            return new Shift[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeDouble(hourlyWage);
    }

    // Getter & Setter
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

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    // Metodo di calcolo delle ore lavorate
    /**
     * Calcola le ore lavorate, considerando anche i turni che attraversano la mezzanotte.
     *
     * @return Ore totali lavorate.
     */
    public double getHoursWorked() {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");
        double start = Integer.parseInt(startParts[0]) + Integer.parseInt(startParts[1]) / 60.0;
        double end = Integer.parseInt(endParts[0]) + Integer.parseInt(endParts[1]) / 60.0;

        // Se l'orario di fine è minore dell'orario di inizio, significa che il turno attraversa la mezzanotte
        if (end < start) {
            end += 24;
        }

        return end - start;
    }

    /**
     * Calcola la paga totale per il turno.
     *
     * @return Paga totale.
     */
    public double getTotalPay() {
        return getHoursWorked() * hourlyWage;
    }
}
