package com.andy.worktrackerapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

import com.andy.worktrackerapp.data.model.Shift;

@Dao
public interface ShiftDao {
    @Insert
    void insertShift(Shift shift);

    @Update
    void updateShift(Shift shift);

    @Delete
    void deleteShift(Shift shift);

    @Query("SELECT * FROM shifts ORDER BY date ASC")
    List<Shift> getAllShifts();

    @Query("SELECT * FROM shifts WHERE strftime('%Y', date) = :year AND strftime('%m', date) = :month ORDER BY date ASC")
    List<Shift> getShiftsByMonthAndYear(String year, String month);

    @Query("SELECT * FROM shifts WHERE strftime('%Y', date) = :year ORDER BY date ASC")
    List<Shift> getShiftsByYear(String year);

    @Query("SELECT SUM(hoursWorked) FROM shifts")
    double getTotalHours();

    @Query("SELECT SUM(hoursWorked * hourlyWage) FROM shifts")
    double getTotalPay();
}
