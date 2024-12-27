//package com.andy.worktrackerapp.ui;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.andy.worktrackerapp.R;
//import com.andy.worktrackerapp.data.model.Shift;
//
//import android.widget.TextView;
//
//import java.util.List;
//
//public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder> {
//
//    private Context context;
//    private List<Shift> shiftList;
//
//    public ShiftAdapter(Context context, List<Shift> shiftList) {
//        this.context = context;
//        this.shiftList = shiftList;
//    }
//
//    @Override
//    public ShiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_shift, parent, false);
//        return new ShiftViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ShiftViewHolder holder, int position) {
//        Shift shift = shiftList.get(position);
//        holder.tvDate.setText("Data: " + shift.getDate());
//        holder.tvTimeInterval.setText(shift.getStartTime() + " - " + shift.getEndTime());
//        holder.tvHoursWorked.setText(String.format("Ore Lavorate: %.2f", shift.getHoursWorked()));
//        holder.tvHourlyWage.setText(String.format("Paga Oraria: %.2f €", shift.getHourlyWage()));
//        holder.tvTotalPay.setText(String.format("Totale: %.2f €", shift.getTotalPay()));
//
//        // Imposta un listener per aprire EditShiftActivity quando si clicca sull'elemento
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Crea un Intent per aprire EditShiftActivity
//                Intent intent = new Intent(context, EditShiftActivity.class);
//                intent.putExtra("shift", shift);
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return shiftList.size();
//    }
//
//    public void setShifts(List<Shift> shifts) {
//        this.shiftList = shifts;
//        notifyDataSetChanged();
//    }
//
//    // ViewHolder per Shift
//    public static class ShiftViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvDate, tvTimeInterval, tvHoursWorked, tvHourlyWage, tvTotalPay;
//
//        public ShiftViewHolder(View itemView) {
//            super(itemView);
//            tvDate = itemView.findViewById(R.id.tvDate);
//            tvTimeInterval = itemView.findViewById(R.id.tvTimeInterval);
//            tvHoursWorked = itemView.findViewById(R.id.tvHoursWorked);
//            tvHourlyWage = itemView.findViewById(R.id.tvHourlyWage);
//            tvTotalPay = itemView.findViewById(R.id.tvTotalPay);
//        }
//    }
//}

package com.andy.worktrackerapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.andy.worktrackerapp.R;
import com.andy.worktrackerapp.data.model.Shift;

import android.widget.TextView;

import java.util.List;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder> {

    private Context context;
    private List<Shift> shiftList;

    public ShiftAdapter(Context context, List<Shift> shiftList) {
        this.context = context;
        this.shiftList = shiftList;
    }

    @Override
    public ShiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shift, parent, false);
        return new ShiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShiftViewHolder holder, int position) {
        Shift shift = shiftList.get(position);
        holder.tvDate.setText("Data: " + shift.getDate());
        holder.tvTimeInterval.setText(shift.getStartTime() + " - " + shift.getEndTime());
        holder.tvHoursWorked.setText(String.format("Ore Lavorate: %.2f", shift.getHoursWorked()));
        holder.tvHourlyWage.setText(String.format("Paga Oraria: %.2f €", shift.getHourlyWage()));
        holder.tvTotalPay.setText(String.format("Totale: %.2f €", shift.getTotalPay()));

        // Imposta un listener per aprire EditShiftActivity quando si clicca sull'elemento
        holder.itemView.setOnClickListener(v -> {
            // Crea un Intent per aprire EditShiftActivity
            Intent intent = new Intent(context, EditShiftActivity.class);
            intent.putExtra("shift", shift);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return shiftList.size();
    }

    public void setShifts(List<Shift> shifts) {
        this.shiftList = shifts;
        notifyDataSetChanged();
    }

    // ViewHolder per Shift
    public static class ShiftViewHolder extends RecyclerView.ViewHolder {

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
}