package com.larry.present.loginregister.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.larry.present.R;
import com.larry.present.bean.teacher.Teacher;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.CheckETEmptyUtil;
import com.larry.present.config.Constants;
import com.larry.present.loginregister.dto.TeacherLoginSuccessDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.teacher.TeacherApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

/*
*    
* 项目名称：present      
* 类描述： 老师提交个人信息的fragment,查看个人信息，修改个人信息
* 创建人：Larry-sea   
* 创建时间：2017/4/16 22:10   
* 修改人：Larry-sea  
* 修改时间：2017/4/16 22:10   
* 修改备注：   
* @version    
*    
*/
public class SubmitTeacherInforActivity extends AppCompatActivity {

    @BindView(R.id.tv_teacher_mail)
    EditText etTeacherMail;
    @BindView(R.id.tv_teacher_info_name)
    EditText etTeacherInfoName;
    @BindView(R.id.toolbar_submit_teacher)
    Toolbar toolbarSubmitTeacher;
    @BindView(R.id.tv_teacher_info_phone)
    EditText etTeacherInfoPhone;

    String schoolId;
    String phone;
    String teacherId;

    /**
     * 提交老师信息observer
     */
    Observer<String> submitTeacherObserver;

    TeacherApi mTeacherApi;

    SubscriberOnNextListener<String> submitTeacherListener;

    //获取老师信息的listener
    SubscriberOnNextListener<TeacherLoginSuccessDto> getTeacherInfoListener;

    CheckETEmptyUtil checkETEmptyUtil = new CheckETEmptyUtil(SubmitTeacherInforActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_teacher_info);
        ButterKnife.bind(this);

        initToolbar();
        initObserver();
        initIntentData();

    }

    /**
     * 初始化监听器
     */
    private void initObserver() {
        submitTeacherListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(SubmitTeacherInforActivity.this, R.string.submit_info_succeed, Toast.LENGTH_SHORT).show();
                //接收到的teacherId为空则表示是从设置页面跳转过来的，则表示用户是注册状态
                if (teacherId == null) {
                    Intent intent = new Intent(SubmitTeacherInforActivity.this, SettingPasswordActivity.class);
                    intent.putExtra(Constants.PHONE, phone);
                    startActivity(intent);
                }
            }

            @Override
            public void onCompleted() {

            }
        };
        submitTeacherObserver = new ProgressSubscriber<>(submitTeacherListener, SubmitTeacherInforActivity.this);

        getTeacherInfoListener = new SubscriberOnNextListener<TeacherLoginSuccessDto>() {
            @Override
            public void onNext(TeacherLoginSuccessDto teacherLoginSuccessDto) {
                initTeacherInfo(teacherLoginSuccessDto);
            }

            @Override
            public void onCompleted() {

            }
        };


    }


    /**
     * 初始化传递过来的数据
     */
    public void initIntentData() {

        if (mTeacherApi == null) {
            mTeacherApi = new TeacherApi(ApiService.getInstance(this).getmRetrofit());
        }
        //接收到的phone为空则表示是从设置页面跳转过来的，则表示用户是查看或者修改信息的状态
        if (schoolId == null || phone == null) {
//            isSubmitInfo = false;
            teacherId = getIntent().getStringExtra("teacherId");
            mTeacherApi.getTeacherInfo(new ProgressSubscriber<>(getTeacherInfoListener, this), teacherId);
        }

    }


    /**
     * 初始化老师信息
     *
     * @return 返回一个已经初始化信息完毕的老师信息
     */
    public Teacher initTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName(etTeacherInfoName.getText().toString().trim());
        teacher.setSchoolId(schoolId);
        teacher.setPhone(phone);
        teacher.setMail(etTeacherMail.getText().toString().trim());
        return teacher;
    }


    public void initToolbar() {
        schoolId = getIntent().getStringExtra(Constants.SCHOOL_ID);
        phone = getIntent().getStringExtra(Constants.PHONE);
        //接收到的phone为空则表示是从设置页面跳转过来的，则表示用户是查看或者修改信息的状态
        if (phone == null) {
            toolbarSubmitTeacher.setTitle(R.string.teacher_info);
        } else {
            toolbarSubmitTeacher.setTitle(R.string.submit_teacher_info);
        }
        toolbarSubmitTeacher.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarSubmitTeacher);
        toolbarSubmitTeacher.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (isSubmitInfo) {
            getMenuInflater().inflate(R.menu.storage_menu, menu);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_menu_save) {
            submitTeacherInfo(initTeacher());
        }
        return true;
    }


    /**
     * 提交老师信息
     *
     * @param teacher
     */
    public void submitTeacherInfo(Teacher teacher) {
        mTeacherApi = new TeacherApi(ApiService.getInstance(SubmitTeacherInforActivity.this).getmRetrofit());
        boolean isEmpty = checkETEmptyUtil.addView(etTeacherInfoName).addTip(R.string.name_cant_empty)
                .addView(etTeacherMail).addTip(R.string.mail_cant_empty).isEmpty();
        //内容不为空
        if (!isEmpty) {
            mTeacherApi.submitTeacherInfo(submitTeacherObserver, teacher);
        }

    }

    /**
     * 初始化老师信息 此时为用户查看或者修改个人信息的状态 所以控件可以修改
     * @param teacherLoginSuccessDto
     */
    public void initTeacherInfo(TeacherLoginSuccessDto teacherLoginSuccessDto) {
        etTeacherInfoName.setText(teacherLoginSuccessDto.getName());
        etTeacherMail.setText(teacherLoginSuccessDto.getMail());
        etTeacherInfoPhone.setText(teacherLoginSuccessDto.getPhone());
    }

}
