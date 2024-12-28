package com.andy.worktrackerapp;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.andy.worktrackerapp.data.dao.ShiftDao;
import com.andy.worktrackerapp.data.model.Shift;
import com.andy.worktrackerapp.data.repository.ShiftRepository;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

public class ShiftRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private final ShiftDao shiftDao = mock(ShiftDao.class);
    private final ShiftRepository repository = new ShiftRepository(null);

    @Test
    public void testInsertShift() {
        Shift shift = new Shift();
        shift.setDate("28-12-2024");
        shift.setStartTime("09:00");
        shift.setEndTime("17:00");
        shift.setHourlyWage(15.0);

        repository.insertShift(shift);

        ArgumentCaptor<Shift> captor = ArgumentCaptor.forClass(Shift.class);
        verify(shiftDao).insertShift(captor.capture());

        assertEquals("28-12-2024", captor.getValue().getDate());
        assertEquals("09:00", captor.getValue().getStartTime());
    }

    @Test
    public void testGetShiftsByMonthAndYear() {
        List<Shift> shifts = new ArrayList<>();
        Shift shift = new Shift();
        shift.setDate("12-2024");
        shifts.add(shift);

        when(shiftDao.getShiftsByMonthAndYear("2024", "12")).thenReturn(shifts);

        repository.getShiftsByMonthAndYear("2024", "12", shiftsLoaded -> {
            assertEquals(1, shiftsLoaded.size());
            assertEquals("12-2024", shiftsLoaded.get(0).getDate());
        });
    }
}