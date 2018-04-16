package com.larry.present.course.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.larry.present.R;
import com.larry.present.bean.course.CourseAndTeacherDto;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.course.CourseApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
*    
* 项目名称：present-android      
* 类描述： 老师查看学生信息的activity
* 创建人：Larry-sea   
* 创建时间：2017/5/13 15:21   
* 修改人：Larry-sea  
* 修改时间：2017/5/13 15:21   
* 修改备注：   
* @version    
*    
*/
public class CheckCourseActivity extends AppCompatActivity {

    //课程id

    String courseId;
    @BindView(R.id.tv_course_info_name)
    TextView tvCourseInfoName;
    @BindView(R.id.toolbar_check_course_info)
    Toolbar toolbarCheckCourseinfo;
    @BindView(R.id.tv_course_info_teacher_name)
    TextView tvCourseInfoTeacherName;
    @BindView(R.id.tv_course_info_phone)
    TextView tvCourseInfoPhone;
    @BindView(R.id.tv_course_info_mail)
    TextView tvCourseInfoMail;

    CourseApi courseApi;


    SubscriberOnNextListener<CourseAndTeacherDto> getCourseTeacherListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        ButterKnife.bind(this);
        initToolbar();
        initView();

    }


    public void initView() {
        courseId = getIntent().getStringExtra("courseId");
        getCourseTeacherListener = new SubscriberOnNextListener<CourseAndTeacherDto>() {
            @Override
            public void onNext(CourseAndTeacherDto courseAndTeacherDto) {
                initView(courseAndTeacherDto);
            }

            @Override
            public void onCompleted() {

            }
        };
        courseApi = new CourseApi(ApiService.getInstance(CheckCourseActivity.this).getmRetrofit());
        courseApi.getTeacherAndCourseInfo(new ProgressSubscriber<CourseAndTeacherDto>(getCourseTeacherListener, CheckCourseActivity.this), courseId);
    }


    /**
     * 初始化课程和老师信息
     *
     * @param courseAndTeacherDto
     */
    public void initView(CourseAndTeacherDto courseAndTeacherDto) {
        if (courseAndTeacherDto == null) {
            return;
        }
        tvCourseInfoName.setText(courseAndTeacherDto.getCourseName());
        tvCourseInfoTeacherName.setText(courseAndTeacherDto.getTeacherName());
        tvCourseInfoMail.setText(courseAndTeacherDto.getTeacherMail());
        tvCourseInfoPhone.setText(courseAndTeacherDto.getTeacherPhone());
    }


    public void initToolbar() {
        toolbarCheckCourseinfo.setTitle(R.string.course_detail_info);
        toolbarCheckCourseinfo.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarCheckCourseinfo);
        toolbarCheckCourseinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
