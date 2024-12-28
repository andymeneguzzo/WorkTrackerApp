package com.andy.worktrackerapp.ui;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.andy.worktrackerapp.R;

public class ShiftViewHolder extends RecyclerView.ViewHolder {

    TextView tvDate, tvTimeInterval, tvHoursWorked, tvHourlyWage, tvTotalPay;

    public ShiftViewHolder(View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvTimeInterval = itemView.findViewById(R.id.tvTimeInterval);
        tvHoursWorked = itemView.findViewById(R.id.tvHoursWorked);
        tvHourlyWage = itemView.findViewById(R.id.tvHourlyWage);
        tvTotalPay = itemView.findViewById(R.id.tvTotalPay);
    }
}