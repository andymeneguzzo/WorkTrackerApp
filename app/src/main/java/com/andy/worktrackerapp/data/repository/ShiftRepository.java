//package com.andy.worktrackerapp.data.repository;
//
//import android.content.Context;
//
//import java.util.List;
//
//import com.andy.worktrackerapp.data.dao.ShiftDao;
//import com.andy.worktrackerapp.data.db.AppDatabase;
//import com.andy.worktrackerapp.data.model.Shift;
//
//public class ShiftRepository {
//    private final ShiftDao shiftDao;
//
//    public ShiftRepository(Context context) {
//        AppDatabase db = AppDatabase.getInstance(context);
//        shiftDao = db.shiftDao();
//    }
//
//    public void insertShift(Shift shift) {
//        shiftDao.insertShift(shift);
//    }
//
//    public void updateShift(Shift shift) {
//        shiftDao.updateShift(shift);
//    }
//
//    public List<Shift> getAllShifts() {
//        return shiftDao.getAllShifts();
//    }
//
//    public List<Shift> getShiftsByMonthAndYear(String year, String month) {
//        return shiftDao.getShiftsByMonthAndYear(year, month);
//    }
//
//    public List<Shift> getShiftsByYear(String year) {
//        return shiftDao.getShiftsByYear(year);
//    }
//
//    public double getTotalHours() {
//        return shiftDao.getTotalHours();
//    }
//
//    public double getTotalPay() {
//        return shiftDao.getTotalPay();
//    }
//}

package com.andy.worktrackerapp.data.repository;

import android.content.Context;

import com.andy.worktrackerapp.data.dao.ShiftDao;
import com.andy.worktrackerapp.data.db.AppDatabase;
import com.andy.worktrackerapp.data.model.Shift;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShiftRepository {

    private final ShiftDao shiftDao;
    private final ExecutorService executorService;

    public ShiftRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        shiftDao = db.shiftDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insertShift(Shift shift) {
        executorService.execute(() -> shiftDao.insertShift(shift));
    }

    public void updateShift(Shift shift) {
        executorService.execute(() -> shiftDao.updateShift(shift));
    }

    public void deleteShift(Shift shift) {
        executorService.execute(() -> shiftDao.deleteShift(shift));
    }

    // Metodo asincrono per ottenere tutti i turni con callback
    public void getAllShifts(ShiftListCallback callback) {
        executorService.execute(() -> {
            List<Shift> shifts = shiftDao.getAllShifts();
            // Passa i dati al callback sul thread principale
            new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> callback.onShiftsLoaded(shifts));
        });
    }

    // Callback per ShiftList
    public interface ShiftListCallback {
        void onShiftsLoaded(List<Shift> shifts);
    }

    // Metodo asincrono per ottenere le statistiche mensili
    public void getMonthlyStats(String year, String month, StatsCallback callback) {
        executorService.execute(() -> {
            List<Shift> shifts = shiftDao.getShiftsByMonthAndYear(year, month);
            double totalHours = 0;
            double totalPay = 0;
            for (Shift shift : shifts) {
                totalHours += shift.getHoursWorked();
                totalPay += shift.getTotalPay();
            }
            // Passa i dati al callback sul thread principale
            double finalTotalHours = totalHours;
            double finalTotalPay = totalPay;
            new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> callback.onStatsLoaded(finalTotalHours, finalTotalPay));
        });
    }

    // Metodo asincrono per ottenere le statistiche annuali
    public void getYearlyStats(String year, StatsCallback callback) {
        executorService.execute(() -> {
            List<Shift> shifts = shiftDao.getShiftsByYear(year);
            double totalHours = 0;
            double totalPay = 0;
            for (Shift shift : shifts) {
                totalHours += shift.getHoursWorked();
                totalPay += shift.getTotalPay();
            }
            // Passa i dati al callback sul thread principale
            double finalTotalHours = totalHours;
            double finalTotalPay = totalPay;
            new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> callback.onStatsLoaded(finalTotalHours, finalTotalPay));
        });
    }

    // Metodo asincrono per ottenere le statistiche totali
    public void getTotalStats(StatsCallback callback) {
        executorService.execute(() -> {
            double totalHours = shiftDao.getTotalHours();
            double totalPay = shiftDao.getTotalPay();
            // Passa i dati al callback sul thread principale
            new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> callback.onStatsLoaded(totalHours, totalPay));
        });
    }

    // Callback per le Statistiche
    public interface StatsCallback {
        void onStatsLoaded(double totalHours, double totalPay);
    }

}