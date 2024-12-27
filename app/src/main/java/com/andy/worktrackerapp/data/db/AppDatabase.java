package com.andy.worktrackerapp.data.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.andy.worktrackerapp.data.dao.ShiftDao;
import com.andy.worktrackerapp.data.model.Shift;

@Database(entities = {Shift.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ShiftDao shiftDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "shifts_db"
                    )
                    .fallbackToDestructiveMigration() // Rimuovi per gestire migrazioni
                    .build();
        }
        return instance;
    }
}