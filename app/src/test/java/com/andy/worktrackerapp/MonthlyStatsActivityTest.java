package com.andy.worktrackerapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.repository.ShiftRepository;
import com.andy.worktrackerapp.ui.MonthlyStatsActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MonthlyStatsActivityTest {

    @Mock
    ShiftRepository repository;

    @Test
    public void testMonthlyStatsDisplay() {
        MonthlyStatsActivity activity = Robolectric.buildActivity(MonthlyStatsActivity.class).create().get();

        RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewMonthlyShifts);
        assertNotNull(recyclerView);
        assertEquals(0, recyclerView.getAdapter().getItemCount());
    }
}