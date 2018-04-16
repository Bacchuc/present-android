package com.larry.present.sign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.larry.present.R;
import com.larry.present.adapter.StudentAbsenceAdapter;
import com.larry.present.bean.sign.StudentCourseSignDto;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.APUtil;
import com.larry.present.common.util.DividerItemDecoration;
import com.larry.present.listener.RecyclerviewClickInterface;
import com.larry.present.loginregister.activity.SubmitStudentInfoActivity;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.sign.SignApi;
import com.larry.present.sign.fragment.ChangeStudentSignStateDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：  老师查看签到结果activity,和老师查看签到历史记录activity(如果是查看历史签到记录activitiy则不显示停止按钮)
* 创建人：Larry-sea   
* 创建时间：2017/5/11 16:14   
* 修改人：Larry-sea  
* 修改时间：2017/5/11 16:14   
* 修改备注：   
* @version    
*    
*/
public class TeacherSignResultActivity extends AppCompatActivity implements RecyclerviewClickInterface {


    @BindView(R.id.sr_student_sign)
    SwipeRefreshLayout srTeacherSign;

    SignApi signApi;
    @BindView(R.id.toolbar_student_sign)
    Toolbar toolbarTeacherSign;
    @BindView(R.id.rv_bases)
    RecyclerView rvBases;


    StudentAbsenceAdapter studentSignAdapter;

    SubscriberOnNextListener<List<StudentCourseSignDto>> studentCourseListener;

    /**
     * 学生签到的链表
     */
    List<StudentCourseSignDto> studentCourseSignDtoList;


    SubscriberOnNextListener<List<StudentCourseSignDto>> studentAbsenceListener;


    /**
     * 课程签到id
     */
    private String courseSignId = "";

    @BindView(R.id.btn_teacher_stop_and__start_sign)
    Button stopSignBtn;


    //是否隐藏停止签到按钮
    boolean hiddenStopBtn;

    //修改过的学生状态数据位置，为了在修改学生状态以后刷新这个学生签到状态的图标
    int changeStudentPosition = -1;

    //修改学生状态的bundle
    Bundle changeStudengSignStateBundle;

    @OnClick(R.id.btn_teacher_stop_and__start_sign)
    public void stopSign(View view) {
        //关闭wifi热点，停止签到
        APUtil.setApEnabled(this, "fasdfasdfasd", "fasfasdfasdf", false);
        //查看签到的汇总信息
        signApi.getAbsenceStudentInfo(new ProgressSubscriber<List<StudentCourseSignDto>>(studentAbsenceListener, TeacherSignResultActivity.this), courseSignId);
        stopSignBtn.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();


    }


    public void initListener() {
        srTeacherSign.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                signApi.getCourseSignInfoOnceByCourseSignId(new ProgressSubscriber<List<StudentCourseSignDto>>(studentCourseListener, TeacherSignResultActivity.this), courseSignId);

            }
        });

        studentAbsenceListener = new SubscriberOnNextListener<List<StudentCourseSignDto>>() {
            @Override
            public void onNext(List<StudentCourseSignDto> studentCourseSignDtos) {
                stopSignBtn.setVisibility(View.VISIBLE);
                //更新数据，显示缺勤的学生信息
                studentCourseSignDtoList = studentCourseSignDtos;
                studentSignAdapter.setData(studentCourseSignDtoList);
                studentSignAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCompleted() {
                srTeacherSign.setRefreshing(false);
            }
        };

        studentCourseListener = new SubscriberOnNextListener<List<StudentCourseSignDto>>() {
            @Override
            public void onNext(List<StudentCourseSignDto> studentCourseSignDtos) {
                studentCourseSignDtoList = studentCourseSignDtos;
                if (studentSignAdapter != null) {
                    //刷新数据
                    studentSignAdapter.notifyDataSetChanged();
                } else {
                    studentSignAdapter = new StudentAbsenceAdapter(TeacherSignResultActivity.this, studentCourseSignDtoList, !hiddenStopBtn);
                    studentSignAdapter.setOnClickListener((RecyclerviewClickInterface) TeacherSignResultActivity.this);
                    //初始化数据
                    // initAdapter(studentCourseSignDtoList);
                    //获取所有学生的签到信息
                    rvBases.setLayoutManager(new LinearLayoutManager(TeacherSignResultActivity.this));
                    rvBases.addItemDecoration(new DividerItemDecoration(TeacherSignResultActivity.this, DividerItemDecoration.VERTICAL_LIST));
                    rvBases.setAdapter(studentSignAdapter);
                }

            }

            @Override
            public void onCompleted() {
                srTeacherSign.setRefreshing(false);
            }
        };


    }


    public void initData() {
        courseSignId = getIntent().getStringExtra("courseSignId");
        hiddenStopBtn = getIntent().getBooleanExtra("hiddenStopBtn", false);
        if (hiddenStopBtn) {
            stopSignBtn.setVisibility(View.INVISIBLE);
        }
        signApi = new SignApi(ApiService.getInstance(this).getmRetrofit());
        signApi.getCourseSignInfoOnceByCourseSignId(new ProgressSubscriber<List<StudentCourseSignDto>>(studentCourseListener, TeacherSignResultActivity.this), courseSignId);


    }


    public void initView() {
        toolbarTeacherSign.setTitle(R.string.check_sign_result);
        toolbarTeacherSign.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarTeacherSign);
        toolbarTeacherSign.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onClick(View view, int position) {
        //如果是老师点击学生签到装态的按钮，则修改该学生的签到状态
        if (view instanceof ImageView) {
            //记录修改学生状态的下标
            changeStudengSignStateBundle = new Bundle();
            changeStudengSignStateBundle.putString("courseSignId", courseSignId);
            changeStudengSignStateBundle.putString("studentId", studentCourseSignDtoList.get(position).getStudentId());

            changeStudentPosition = position;
            ChangeStudentSignStateDialog changeStudentSignStateDialog = new ChangeStudentSignStateDialog();
            changeStudentSignStateDialog.setArguments(changeStudengSignStateBundle);
            changeStudentSignStateDialog.show(getSupportFragmentManager(), "changeStudentSignDialog");

        }
        //如果是老师点击整个大的itemView则是查看学生信息
        else {
            Intent intent = new Intent(this, SubmitStudentInfoActivity.class);
            intent.putExtra("studentId", studentCourseSignDtoList.get(position).getStudentId());
            startActivity(intent);
        }


    }

    @Override
    public void onItemLongClick(View view, int position) {

    }


    //修改学生签到，dialogFragment回调这个方法
    public void changeStudentSignState(String signState) {
        if (changeStudentPosition != -1) {
            //修改学生转态，并且刷新视图
            studentCourseSignDtoList.get(changeStudentPosition).setSignState(signState);
            studentSignAdapter.setData(studentCourseSignDtoList);
            studentSignAdapter.notifyDataSetChanged();
        }
    }
}
