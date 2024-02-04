package com.example.mycollegeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ScheduleItem> scheduleList;
    private static OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick (int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView courseName;
        public TextView courseTime;
        public TextView courseInstructor;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            courseName = itemView.findViewById(R.id.cnId);
            courseTime = itemView.findViewById(R.id.ctId);
            courseInstructor = itemView.findViewById(R.id.ciId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position) ;
                        }
                    }
                }
            });
        }
    }
    public MyAdapter (ArrayList<ScheduleItem> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_item, parent, false);
        ViewHolder vh = new ViewHolder(v, listener);
        return vh;
    }
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleItem curr = scheduleList.get(position);
        holder.courseName.setText(curr.getClassName());
        holder.courseTime.setText(curr.getClassTime());
        holder.courseInstructor.setText(curr.getClassInstructor());

    }
    public int getItemCount() {
        return scheduleList.size();
    }
}

