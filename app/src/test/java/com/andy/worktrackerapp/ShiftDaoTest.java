package com.andy.worktrackerapp;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.andy.worktrackerapp.data.dao.ShiftDao;
import com.andy.worktrackerapp.data.db.AppDatabase;
import com.andy.worktrackerapp.data.model.Shift;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ShiftDaoTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private AppDatabase database;
    private ShiftDao shiftDao;

    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase.class
        ).allowMainThreadQueries().build();
        shiftDao = database.shiftDao();
    }

    @After
    public void tearDown() {
        database.close();
    }

    @Test
    public void testInsertAndRetrieveShift() {
        Shift shift = new Shift();
        shift.setDate("28-12-2024");
        shift.setStartTime("09:00");
        shift.setEndTime("17:00");
        shift.setHourlyWage(15.0);

        shiftDao.insertShift(shift);

        List<Shift> shifts = shiftDao.getAllShifts();
        assertEquals(1, shifts.size());
        assertEquals("28-12-2024", shifts.get(0).getDate());
    }

    @Test
    public void testGetShiftsByMonthAndYear() {
        Shift shift = new Shift();
        shift.setDate("28-12-2024");
        shift.setStartTime("09:00");
        shift.setEndTime("17:00");
        shift.setHourlyWage(15.0);

        shiftDao.insertShift(shift);

        List<Shift> shifts = shiftDao.getShiftsByMonthAndYear("2024", "12");
        assertEquals(1, shifts.size());
        assertEquals("28-12-2024", shifts.get(0).getDate());
    }
}