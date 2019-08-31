package com.carebearlover.learnenglish.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carebearlover.learnenglish.R;
import com.carebearlover.learnenglish.models.ExpressionModel;

import java.util.Calendar;
import java.util.List;

public class expressionRVAdapter extends RecyclerView.Adapter<expressionRVAdapter.expressionsRecyclerViewHolder> {

    private long time;
    private List<ExpressionModel> models;

    public expressionRVAdapter(List<ExpressionModel> models) {
        this.models = models;
    }

    static class expressionsRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView dateRepeating;
        TextView wordTextView;
        RadioButton radioButton1;
        RadioButton radioButton2;
        RadioButton radioButton3;
        RadioButton radioButton4;
        RadioButton radioButton5;
        RadioButton radioButton6;

        expressionsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            dateRepeating = itemView.findViewById(R.id.date_repeating);
            radioButton1 = itemView.findViewById(R.id.first_repeat);
            radioButton2 = itemView.findViewById(R.id.second_repeat);
            radioButton3 = itemView.findViewById(R.id.third_repeat);
            radioButton4 = itemView.findViewById(R.id.fourth_repeat);
            radioButton5 = itemView.findViewById(R.id.fifth_repeat);
            radioButton6 = itemView.findViewById(R.id.sixth_repeat);
            wordTextView = itemView.findViewById(R.id.wordTextView);
        }
    }

    @NonNull
    @Override
    public expressionRVAdapter.expressionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_word_item,
                parent, false);
        return new expressionsRecyclerViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull expressionRVAdapter.expressionsRecyclerViewHolder holder, int position) {
        holder.wordTextView.setText(models.get(position).getTitle());
        String[] monthName = {"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля",
                "Августа", "Сентября", "Октября", "Ноября",
                "Декабря"};
        Calendar calendar;
        time = models.get(position).getDate();
        int group = models.get(position).getGroup();
        if (group == 1) {
            holder.radioButton1.setChecked(true);
            calendar = setCalendar();
            holder.dateRepeating.setText(" " + (calendar.get(Calendar.DAY_OF_MONTH)) + " "
                    + monthName[calendar.get(Calendar.MONTH)]);
        } else if (group == 2) {
            holder.radioButton2.setChecked(true);
            calendar = setCalendar();
            holder.dateRepeating.setText(" " + (calendar.get(Calendar.DAY_OF_MONTH)) + " "
                    + monthName[calendar.get(Calendar.MONTH)]);
        } else if (group == 3) {
            holder.radioButton3.setChecked(true);
            calendar = setCalendar();
            holder.dateRepeating.setText(" " + (calendar.get(Calendar.DAY_OF_MONTH)) + " "
                    + monthName[calendar.get(Calendar.MONTH)]);
        } else if (group == 4) {
            holder.radioButton4.setChecked(true);
            calendar = setCalendar();
            holder.dateRepeating.setText(" " + (calendar.get(Calendar.DAY_OF_MONTH)) + " "
                    + monthName[calendar.get(Calendar.MONTH)]);
        } else if (group == 5) {
            holder.radioButton5.setChecked(true);
            calendar = setCalendar();
            holder.dateRepeating.setText(" " + (calendar.get(Calendar.DAY_OF_MONTH)) + " "
                    + monthName[calendar.get(Calendar.MONTH)]);
        } else if (group == 6) {
            holder.radioButton6.setChecked(true);
            calendar = setCalendar();
            holder.dateRepeating.setText(" " + (calendar.get(Calendar.DAY_OF_MONTH)) + " "
                    + monthName[calendar.get(Calendar.MONTH)]);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    private Calendar setCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        return calendar;
    }
}
