package com.larry.present.sign.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.bean.sign.StudentSignInfoOfTermDto;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.sign.SignApi;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
*    
* 项目名称：present-android      
* 类描述： 老师查看某个课程下面的的某个班级的全体学生学期汇总情况
* 创建人：Larry-sea   
* 创建时间：2017/5/14 16:54   
* 修改人：Larry-sea  
* 修改时间：2017/5/14 16:54   
* 修改备注：   
* @version    
*    
*/
public class CheckCourseSignInfoOnTermActivity extends AppCompatActivity {

    SignApi signApi;

    @BindView(R.id.rv_bases)
    RecyclerView rvBases;

    SubscriberOnNextListener<List<StudentSignInfoOfTermDto>> getCourseSignInfoOnTermListener;

    //课程id
    String courseId;
    //班级id
    String classId;


    StringBuilder stringBuilder;
    @BindView(R.id.toolbar_course_sign_count)
    Toolbar toolbarCourseSignCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_sign_count);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }


    public void initData() {
        courseId = getIntent().getStringExtra("courseId");
        classId = getIntent().getStringExtra("classId");
        signApi = new SignApi(ApiService.getInstance(this).getmRetrofit());
        signApi.getCourseSignInfoInTerm(new ProgressSubscriber<List<StudentSignInfoOfTermDto>>(getCourseSignInfoOnTermListener, this), AccountManager.getTeacher().getId(), courseId, classId);

    }


    public void initListener() {
        stringBuilder = new StringBuilder();
        getCourseSignInfoOnTermListener = new SubscriberOnNextListener<List<StudentSignInfoOfTermDto>>() {
            @Override
            public void onNext(List<StudentSignInfoOfTermDto> studentSignInfoOfTermDtos) {
                rvBases.setAdapter(new CommonAdapter<StudentSignInfoOfTermDto>(CheckCourseSignInfoOnTermActivity.this, R.layout.student_sign_info_item, studentSignInfoOfTermDtos) {
                    @Override
                    protected void convert(ViewHolder holder, StudentSignInfoOfTermDto studentSignInfoOfTermDto, int position) {
                        holder.setText(R.id.tv_student_sign_info_number, studentSignInfoOfTermDto.getStudentNumber());
                        holder.setText(R.id.tv_student_sign_info_name, studentSignInfoOfTermDto.getName());
                        stringBuilder.delete(0, stringBuilder.length() - 1);
                        stringBuilder.append(studentSignInfoOfTermDto.getSign());
                        stringBuilder.append(" ");
                        stringBuilder.append(studentSignInfoOfTermDto.getSickLeave());
                        stringBuilder.append(" ");
                        stringBuilder.append(studentSignInfoOfTermDto.getAbsence());
                        holder.setText(R.id.tv_student_sign_info_count, stringBuilder.toString());

                    }
                });


            }

            @Override
            public void onCompleted() {

            }
        };

    }


    public void initView() {

        toolbarCourseSignCount.setTitle(R.string.sign_info_count);
        toolbarCourseSignCount.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarCourseSignCount);
        toolbarCourseSignCount.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}
