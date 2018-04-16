package com.larry.present.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.common.util.ApplicationUtil;
import com.larry.present.common.util.CommonUtil;
import com.larry.present.config.Constants;
import com.larry.present.loginregister.activity.SubmitStudentInfoActivity;
import com.larry.present.loginregister.activity.SubmitTeacherInforActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述： 设置activity
* 创建人：Larry-sea   
* 创建时间：2017/5/26 11:23   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 11:23   
* 修改备注：   
* @version    
*    
*/
public class SettingActivity extends AppCompatActivity {

    Intent intent;
    @BindView(R.id.toolbar_check_course_info)
    Toolbar toolbarCheckCourseInfo;
    @BindView(R.id.tv_setting_version)
    TextView tvSettingVersion;

    @OnClick(R.id.id_layout_setting_change_password)
    public void changePassword(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    @OnClick(R.id.id_layout_setting_feedback)
    public void submitFeedback(View view) {
        startActivity(new Intent(this, FeedbackActivity.class));
    }

    @OnClick(R.id.id_layout_setting_check_person_info)
    public void checkPersonInfo(View view) {
        openPersonInfoActivity();

    }


    @OnClick(R.id.id_layout_setting_share)
    public void share(View view) {
        startActivity(CommonUtil.shareInfor(this, R.string.this_is_nice_app_you_can_download));
    }

    @OnClick(R.id.iv_setting_version_update)
    public void checkVersion(View view) {
        //todo 检查更新，通过下载
        Toast.makeText(this, "这个还未做", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initToolbar();
    }


    public void initToolbar() {
        toolbarCheckCourseInfo.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbarCheckCourseInfo.setTitle(R.string.setting);
        setSupportActionBar(toolbarCheckCourseInfo);
        toolbarCheckCourseInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        try {
            tvSettingVersion.setText(ApplicationUtil.getVersionName(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 打开个人信息activity
     */
    private void openPersonInfoActivity() {
        String userId = AccountManager.getStudent() == null ? AccountManager.getTeacher().getId()
                : AccountManager.getStudent().getId();
        int userType = AccountManager.returnLoginUserType();
        //学生用户类型，查看信息
        if (userType == Constants.STUDENT_TYPE) {
            Intent intent = new Intent(this, SubmitStudentInfoActivity.class);
            intent.putExtra("studentId", AccountManager.getStudent().getId());
            startActivity(intent);
        }
        //老师用户类型，查看信息
        else if (userType == Constants.TEACHER_TYPE) {
            Intent intent = new Intent(this, SubmitTeacherInforActivity.class);
            intent.putExtra("teacherId", AccountManager.getTeacher().getId());
            startActivity(intent);
        }
    }

}
