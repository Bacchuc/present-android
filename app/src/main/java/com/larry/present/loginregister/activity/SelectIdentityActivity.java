package com.larry.present.loginregister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.larry.present.R;
import com.larry.present.config.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：用户初始化选择学生身份的activity
* 创建人：Larry-sea   
* 创建时间：2017/5/5 9:36   
* 修改人：Larry-sea  
* 修改时间：2017/5/5 9:36   
* 修改备注：   
* @version    
*    
*/
public class SelectIdentityActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_course_info)
    Toolbar toolbarSelectSchoole;
    @BindView(R.id.tv_identity_school)
    TextView tvIdentitySchool;


    /**
     * 学校id
     */
    String schoolId;

    /*
    * 学校名称
    * */
    String schoolName;

    //手机号
    String phone;


    @OnClick(R.id.rb_identity_student)
    void studentOnClick(View view) {
        Intent intent = new Intent(SelectIdentityActivity.this, SubmitStudentInfoActivity.class);
        intent.putExtra(Constants.SCHOOL_ID, schoolId);
        intent.putExtra(Constants.SCHOOLE_NAME, schoolName);
        intent.putExtra(Constants.PHONE, phone);
        startActivity(intent);

    }

    @OnClick(R.id.rb_identity_teacher)
    void teacherOnClick(View view) {
        Intent intent = new Intent(SelectIdentityActivity.this, SubmitTeacherInforActivity.class);
        intent.putExtra(Constants.SCHOOL_ID, schoolId);
        intent.putExtra(Constants.SCHOOLE_NAME, schoolName);
        intent.putExtra(Constants.PHONE, phone);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_identity);
        ButterKnife.bind(this);
        initToolbar();
        schoolId = getIntent().getStringExtra(Constants.SCHOOL_ID);
        schoolName = getIntent().getStringExtra(Constants.SCHOOLE_NAME);
        phone = getIntent().getStringExtra(Constants.PHONE);
        tvIdentitySchool.setText(schoolName + "的");
    }


    private void initToolbar() {
        toolbarSelectSchoole.setTitle(R.string.select_identity);
        toolbarSelectSchoole.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarSelectSchoole);
        toolbarSelectSchoole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
