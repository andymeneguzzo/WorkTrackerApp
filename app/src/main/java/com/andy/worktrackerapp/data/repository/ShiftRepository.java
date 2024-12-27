package com.andy.worktrackerapp.data.repository;

import android.content.Context;

import java.util.List;

import com.andy.worktrackerapp.data.dao.ShiftDao;
import com.andy.worktrackerapp.data.db.AppDatabase;
import com.andy.worktrackerapp.data.model.Shift;

public class ShiftRepository {
    private final ShiftDao shiftDao;

    public ShiftRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        shiftDao = db.shiftDao();
    }

    public void insertShift(Shift shift) {
        shiftDao.insertShift(shift);
    }

    public void updateShift(Shift shift) {
        shiftDao.updateShift(shift);
    }

    public List<Shift> getAllShifts() {
        return shiftDao.getAllShifts();
    }

    public List<Shift> getShiftsByMonthAndYear(String year, String month) {
        return shiftDao.getShiftsByMonthAndYear(year, month);
    }

    public List<Shift> getShiftsByYear(String year) {
        return shiftDao.getShiftsByYear(year);
    }

    public double getTotalHours() {
        return shiftDao.getTotalHours();
    }

    public double getTotalPay() {
        return shiftDao.getTotalPay();
    }
}
