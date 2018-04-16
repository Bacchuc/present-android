package com.larry.present.sign.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.larry.present.R;
import com.larry.present.adapter.StudentAbsenceAdapter;
import com.larry.present.bean.sign.CourseSignInfoDto;
import com.larry.present.common.util.DividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
*    
* 项目名称：present-android      
* 类描述： 查看某个课程的的汇签到信息
* 创建人：Larry-sea   
* 创建时间：2017/5/18 18:48   
* 修改人：Larry-sea  
* 修改时间：2017/5/18 18:48   
* 修改备注：   
* @version    
*    
*/
public class StudentCheckDetailSignInfoActivity extends AppCompatActivity {
    @BindView(R.id.rv_bases)
    RecyclerView rvBases;
    @BindView(R.id.toolbar_student_check_sign_detail)
    Toolbar toolbarStudentCheckSignDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_check_detail);
        ButterKnife.bind(this);
        initToolbar();
        initData();
    }


    public void initData() {
        List<CourseSignInfoDto> courseSignInfoDtoList = (List<CourseSignInfoDto>) getIntent().getSerializableExtra("courseSignInfoList");
        rvBases.setLayoutManager(new LinearLayoutManager(this));
        rvBases.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rvBases.setAdapter(new CommonAdapter<CourseSignInfoDto>(StudentCheckDetailSignInfoActivity.this, R.layout.student_sign_state_item, courseSignInfoDtoList) {
            @Override
            protected void convert(ViewHolder holder, CourseSignInfoDto courseSignInfoDto, int position) {
                holder.setText(R.id.tv_student_state_number, String.valueOf(position + 1));
                holder.setText(R.id.tv_student_state_name, courseSignInfoDto.getSignTime());
                Glide.with(StudentCheckDetailSignInfoActivity.this).load(StudentAbsenceAdapter.initStateDrawable(courseSignInfoDto.getSignState())).into((ImageView) holder.getView(R.id.iv_student_state));
            }
        });
    }


    public void initToolbar() {
        toolbarStudentCheckSignDetail.setTitle(R.string.check_sign_detail_info);
        toolbarStudentCheckSignDetail.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarStudentCheckSignDetail);
        toolbarStudentCheckSignDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
