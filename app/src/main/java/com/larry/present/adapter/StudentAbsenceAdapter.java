package com.larry.present.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.larry.present.R;
import com.larry.present.bean.sign.StudentCourseSignDto;
import com.larry.present.config.Constants;
import com.larry.present.listener.RecyclerviewClickInterface;
import com.larry.present.viewholder.StudentCourseSignStateViewHolder;

import java.util.List;

;


/**
 * 学生缺勤的适配器
 */
public class StudentAbsenceAdapter extends RecyclerView.Adapter<StudentCourseSignStateViewHolder> {

    List<StudentCourseSignDto> studentCourseSignDtoList;
    Context mcontext;
    RecyclerviewClickInterface mrecyclerClickInterface;


    //是否是签到状态
    boolean isSigning;

    /**
     * @param context
     * @param studentCourseSignDtoList 学生签到记录
     * @param isSigning                是否是签到中查看结果还是，查看历史记录，如果是签到过程中查看，则isSigning为true,否则为false
     */
    public StudentAbsenceAdapter(Context context, List<StudentCourseSignDto> studentCourseSignDtoList, boolean isSigning) {
        this.mcontext = context;
        this.studentCourseSignDtoList = studentCourseSignDtoList;
        this.isSigning = isSigning;

    }

    @Override
    public StudentCourseSignStateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentCourseSignStateViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.student_sign_state_item, parent, false), (Activity) mcontext);
    }

    @Override
    public void onBindViewHolder(StudentCourseSignStateViewHolder holder, int position) {
        if (holder != null) {
            holder.name.setText(studentCourseSignDtoList.get(position).getName());
            holder.number.setText(studentCourseSignDtoList.get(position).getStudentNumber());
            Glide.with(mcontext).load(initStateDrawable(studentCourseSignDtoList.get(position).getSignState())).into(holder.stateIv);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //查看这个学生信息
                    mrecyclerClickInterface.onClick(v, position);
                }
            });
            //如果是老师签到的情形
            if (isSigning) {
                holder.stateIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //将改变状态的imageView作为参数传输过去
                        mrecyclerClickInterface.onClick(v, position);
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return studentCourseSignDtoList != null ? studentCourseSignDtoList.size() : 0;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnClickListener(RecyclerviewClickInterface recyclerviewClickInterface) {
        this.mrecyclerClickInterface = recyclerviewClickInterface;
    }

    public static int initStateDrawable(String state) {
        int drawableId = R.drawable.ic_fork_48;
        switch (state) {
            case Constants.STUDENT_ABSENCE:
                drawableId = R.drawable.ic_fork_48;
                break;
            case Constants.STUDENT_SICK_LEAVE:
                drawableId = R.drawable.ic_circle_48;
                break;
            case Constants.STUDENT_SIGN:
                drawableId = R.drawable.ic_check_48;
                break;
        }
        return drawableId;

    }


    public void setData(List<StudentCourseSignDto> studentCourseSignDtos) {
        this.studentCourseSignDtoList = studentCourseSignDtos;

    }

}
