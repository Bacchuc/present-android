package com.larry.present.sign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.bean.sign.CourseSignInfoDto;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.sign.SignApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
*    
* 项目名称：present-android      
* 类描述：  学生查看某个课程的所有自己的签到记录activity,这个是查看每次的详细签到记录的
* 创建人：Larry-sea   
* 创建时间：2017/5/18 16:02   
* 修改人：Larry-sea  
* 修改时间：2017/5/18 16:02   
* 修改备注：   
* @version    
*    
*/
public class StudentCheckCourseSignInfoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_student_sign)
    Toolbar toolbarStudentSign;


    SignApi signApi;

    SubscriberOnNextListener<List<CourseSignInfoDto>> getCourseSignInfoListener;

    String courseId;
    @BindView(R.id.tv_course_sign_sign_times)
    TextView tvCourseSignSignTimes;
    @BindView(R.id.tv_course_sign_sick_times)
    TextView tvCourseSignSickTimes;
    @BindView(R.id.tv_course_sign_absence_times)
    TextView tvCourseSignAbsenceTimes;

    List<Integer> signTimes;

    List<CourseSignInfoDto> courseSignInfoDtoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_check_course_sign_info);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    public void initData() {
        courseId = getIntent().getStringExtra("courseId");
        signApi = new SignApi(ApiService.getInstance(this).getmRetrofit());
        getCourseSignInfoListener = new SubscriberOnNextListener<List<CourseSignInfoDto>>() {
            @Override
            public void onNext(List<CourseSignInfoDto> courseSignInfoDtos) {
                signTimes = countSignTimes(courseSignInfoDtos);
                courseSignInfoDtoList = courseSignInfoDtos;
                initTimes(countSignTimes(courseSignInfoDtoList));

            }

            @Override
            public void onCompleted() {

            }
        };
        signApi.studentGetCourseSignInfoDto(new ProgressSubscriber<List<CourseSignInfoDto>>(getCourseSignInfoListener, this), courseId, AccountManager.getStudent().getId());

    }

    public void initView() {
        toolbarStudentSign.setTitle(R.string.check_course_sing_info);
        toolbarStudentSign.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarStudentSign);
        toolbarStudentSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    public List<Integer> countSignTimes(final List<CourseSignInfoDto> courseSignInfoDtos) {
        List<Integer> signTimeList = new ArrayList<>(3);
        Integer signTimes = 0;
        Integer absenceTimes = 0;
        Integer sickLeaveTimes = 0;
        for (CourseSignInfoDto courseSignInfoDto : courseSignInfoDtos) {
            if (courseSignInfoDto.getSignState().equals("1")) {
                signTimes++;
            } else if (courseSignInfoDto.getSignState().equals("2")) {
                sickLeaveTimes++;
            } else if (courseSignInfoDto.getSignState().equals("3")) {
                absenceTimes++;
            }
        }
        signTimeList.add(signTimes);
        signTimeList.add(sickLeaveTimes);
        signTimeList.add(absenceTimes);
        return signTimeList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_course_sign, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_student_course_sign_menu_check) {
            Intent intent = new Intent(this, StudentCheckDetailSignInfoActivity.class);
            intent.putExtra("courseSignInfoList", (Serializable) courseSignInfoDtoList);
            startActivity(intent);
        }

        return true;
    }


    public void initTimes(List<Integer> signTimesList) {
        tvCourseSignSignTimes.setText("你已签到" + String.valueOf(signTimesList.get(0)) + "次");
        tvCourseSignSickTimes.setText("你已请假" + String.valueOf(signTimesList.get(1)) + "次");
        tvCourseSignAbsenceTimes.setText("你已缺勤" + String.valueOf(signTimesList.get(2)) + "次");


    }


}
