package com.larry.present.viewholder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.larry.present.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentCourseSignStateViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_student_state_number)
    public TextView number;
    @BindView(R.id.tv_student_state_name)
    public TextView name;
    @BindView(R.id.iv_student_state)
    public ImageView stateIv;

    public StudentCourseSignStateViewHolder(View itemView, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}