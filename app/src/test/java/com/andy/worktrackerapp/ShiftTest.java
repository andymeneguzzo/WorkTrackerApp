package com.andy.worktrackerapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.andy.worktrackerapp.data.model.Shift;

public class ShiftTest {

    @Test
    public void testGetHoursWorked_SameDay() {
        Shift shift = new Shift();
        shift.setStartTime("09:00");
        shift.setEndTime("17:00");

        assertEquals(8.0, shift.getHoursWorked(), 0.01);
    }

    @Test
    public void testGetHoursWorked_AcrossMidnight() {
        Shift shift = new Shift();
        shift.setStartTime("22:00");
        shift.setEndTime("06:00");

        assertEquals(8.0, shift.getHoursWorked(), 0.01);
    }

    @Test
    public void testGetTotalPay() {
        Shift shift = new Shift();
        shift.setStartTime("09:00");
        shift.setEndTime("17:00");
        shift.setHourlyWage(15.0);

        assertEquals(120.0, shift.getTotalPay(), 0.01);
    }
}