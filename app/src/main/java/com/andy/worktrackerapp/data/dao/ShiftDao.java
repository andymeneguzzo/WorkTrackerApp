//package com.andy.worktrackerapp.data.dao;
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Delete;
//import androidx.room.Update;
//
//import com.andy.worktrackerapp.data.model.Shift;
//
//import java.util.List;
//
//@Dao
//public interface ShiftDao {
//
//    @Insert
//    void insertShift(Shift shift);
//
//    @Update
//    void updateShift(Shift shift); // Presente per aggiornare i turni
//
//    @Delete
//    void deleteShift(Shift shift);
//
//    @Query("SELECT * FROM shifts ORDER BY date ASC")
//    List<Shift> getAllShifts();
//
//    // Query per filtrare i turni di un determinato mese e anno
//    @Query("SELECT * FROM shifts WHERE strftime('%Y', substr(date, 7, 4)) = :year AND strftime('%m', substr(date, 4, 2)) = :month ORDER BY date ASC")
//    List<Shift> getShiftsByMonthAndYear(String year, String month);
//
//    // Query per filtrare i turni di un determinato anno
//    @Query("SELECT * FROM shifts WHERE strftime('%Y', substr(date, 7, 4)) = :year ORDER BY date ASC")
//    List<Shift> getShiftsByYear(String year);
//
//    @Query("SELECT SUM((CAST(substr(endTime, 1, 2) AS REAL) + CAST(substr(endTime, 4, 2) AS REAL)/60) - (CAST(substr(startTime, 1, 2) AS REAL) + CAST(substr(startTime, 4, 2) AS REAL)/60)) FROM shifts")
//    double getTotalHours();
//
//    @Query("SELECT SUM((CAST(substr(endTime, 1, 2) AS REAL) + CAST(substr(endTime, 4, 2) AS REAL)/60) - (CAST(substr(startTime, 1, 2) AS REAL) + CAST(substr(startTime, 4, 2) AS REAL)/60)) * hourlyWage FROM shifts")
//    double getTotalPay();
//}

package com.andy.worktrackerapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.andy.worktrackerapp.data.model.Shift;

import java.util.List;

@Dao
public interface ShiftDao {

    @Insert
    void insertShift(Shift shift);

    @Update
    void updateShift(Shift shift);

    @Delete
    void deleteShift(Shift shift);

    @Query("SELECT * FROM shifts ORDER BY substr(date, 7, 4) DESC, substr(date, 4, 2) DESC, substr(date, 1, 2) DESC")
    List<Shift> getAllShifts();

    // Query per filtrare i turni di un determinato mese e anno
    @Query("SELECT * FROM shifts WHERE substr(date, 7, 4) = :year AND substr(date, 4, 2) = :month ORDER BY substr(date, 7, 4) DESC, substr(date, 4, 2) DESC, substr(date, 1, 2) DESC")
    List<Shift> getShiftsByMonthAndYear(String year, String month);

    // Query per filtrare i turni di un determinato anno
    @Query("SELECT * FROM shifts WHERE substr(date, 7, 4) = :year ORDER BY substr(date, 7, 4) DESC, substr(date, 4, 2) DESC, substr(date, 1, 2) DESC")
    List<Shift> getShiftsByYear(String year);

    // Query per calcolare le ore totali lavorate
    @Query("SELECT SUM((CAST(substr(endTime, 1, 2) AS REAL) + CAST(substr(endTime, 4, 2) AS REAL)/60) - (CAST(substr(startTime, 1, 2) AS REAL) + CAST(substr(startTime, 4, 2) AS REAL)/60)) FROM shifts")
    double getTotalHours();

    // Query per calcolare il totale pagato
    @Query("SELECT SUM(((CAST(substr(endTime, 1, 2) AS REAL) + CAST(substr(endTime, 4, 2) AS REAL)/60) - (CAST(substr(startTime, 1, 2) AS REAL) + CAST(substr(startTime, 4, 2) AS REAL)/60)) * hourlyWage) FROM shifts")
    double getTotalPay();
}