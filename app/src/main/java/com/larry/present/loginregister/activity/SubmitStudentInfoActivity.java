package com.larry.present.loginregister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.larry.present.R;
import com.larry.present.bean.classes.Classes;
import com.larry.present.bean.student.Student;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.CheckETEmptyUtil;
import com.larry.present.common.util.GetUniqueNameUtil;
import com.larry.present.config.ApiConfig;
import com.larry.present.config.Constants;
import com.larry.present.loginregister.dto.StudentLoginSuccessDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.classes.ClassApi;
import com.larry.present.network.student.StudentApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：  学生提交个个人信息接口，和展现信息个人activity
* 创建人：Larry-sea   
* 创建时间：2017/4/19 14:48   
* 修改人：Larry-sea  
* 修改时间：2017/4/19 14:48   
* 修改备注：   
* @version    
*    
*/
public class SubmitStudentInfoActivity extends AppCompatActivity {


    @BindView(R.id.cb_student_male)
    CheckBox cbStudentMale;
    @BindView(R.id.cb_student_female)
    CheckBox cbStudentFemale;
    @BindView(R.id.iv_student_portrait)
    ImageView ivStudentPortrait;
    @BindView(R.id.et_student_name)
    EditText etStudentName;
    @BindView(R.id.et_student_student_number)
    EditText etStudentStudentNumber;
    @BindView(R.id.et_student_class)
    EditText etStudentClass;
    @BindView(R.id.et_student_school)
    EditText etStudentSchool;
    @BindView(R.id.et_student_position)
    EditText etStudentPosition;
    @BindView(R.id.et_student_mail)
    EditText etStudentMail;
    @BindView(R.id.et_student_phone)
    EditText etStudentPhone;

    @BindView(R.id.toolbar_student_info)
    Toolbar toolbarStudentInfo;

    /**
     * 设置头像
     *
     * @param view
     */
    @OnClick(R.id.iv_student_portrait)
    void setPortrait(View view) {
        if (schoolId != null) {
            Intent intent = new Intent(this, StudentSetPortraitActivity.class);
            intent.putExtra(Constants.PHONE, phone);
            startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent(this, CheckPortraitActivity.class);
            intent.putExtra("portraitBmp", netWorkPortraitPath);
            startActivity(intent);
        }
    }

    String netWorkPortraitPath;

    final static String TAG = SubmitStudentInfoActivity.class.toString();

    /**
     * 提交信息的回调接口
     */
    SubscriberOnNextListener mSubmitInfoNextListener;

    /**
     * 获取班级id的listener
     */
    SubscriberOnNextListener<String> getClassIdOnNextListener;

    /**
     * 添加班级的监听器
     */
    SubscriberOnNextListener<String> addClassSubscriberListener;

    /**
     * 获取学生个人信息的dto
     */
    SubscriberOnNextListener<StudentLoginSuccessDto> getStudentInfoListener;

    /**
     * 获取学生个人信息的订阅者
     */
    ProgressSubscriber<StudentLoginSuccessDto> getStudentInfoSubscriber;

    /**
     * 获取班级的idsubscriber
     */
    ProgressSubscriber<String> getClassIdSubscriber;

    /**
     * 添加班级的订阅者
     */
    ProgressSubscriber<Classes> addClassSubscriber;

    /**
     * 提交个人信息的subscriber
     */
    ProgressSubscriber<String> submitInfoSubscriber;

    /**
     * 学生的接口api
     */
    StudentApi mStudentApi;

    /**
     * 班级api
     */
    ClassApi mClassApi;

    /**
     * 学生个人信息
     */
    Student mStudent;

    /**
     * 学校id
     */
    String mSchoolId;

    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 学校id
     */
    private String schoolId;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 学生头像地址
     */
    private String portraitPath;

    /**
     * 手机号
     */
    private String phone;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == -1) {
            portraitPath = data.getStringExtra(Constants.PORTRAITPATH);
            Glide.with(SubmitStudentInfoActivity.this).load(portraitPath).into(ivStudentPortrait);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_info);
        ButterKnife.bind(this);
        studentId = getIntent().getStringExtra("studentId");
        initToolBar();
        initData();

        //如果学生id不为空代表是展示学生的个人信息
        initListener();
        if (studentId != null) {
            mStudentApi.getStudentInfo(new ProgressSubscriber<StudentLoginSuccessDto>(getStudentInfoListener, this), studentId);
        }
        //代表提交个人信息或者是修改个人信息
        else {
            schoolName = getIntent().getStringExtra(Constants.SCHOOLE_NAME);
            schoolId = getIntent().getStringExtra(Constants.SCHOOL_ID);
            phone = getIntent().getStringExtra(Constants.PHONE);
        }
        initView();
    }

    private void initToolBar() {
        toolbarStudentInfo.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        if (studentId != null) {
            toolbarStudentInfo.setTitle(R.string.student_info);
        } else {
            toolbarStudentInfo.setTitle(R.string.edit_student_info);
        }
        setSupportActionBar(toolbarStudentInfo);
        toolbarStudentInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void initData() {
        if (getIntent() != null && getIntent().getStringExtra(Constants.SCHOOL_ID) != null) {
            mSchoolId = getIntent().getStringExtra(Constants.SCHOOL_ID);
        }
        mStudentApi = new StudentApi(ApiService.getInstance(SubmitStudentInfoActivity.this).getmRetrofit());

    }

    /**
     * 获取所有学生信息
     *
     * @param classId 班级id
     * @return
     */
    public Student initStudentInfo(String classId) {
        //todo 权限处理
        String imel = GetUniqueNameUtil.getUniqueName();
        Student student = new Student();
        student.setName(etStudentName.getText().toString().trim());
        student.setStudentNumber(etStudentStudentNumber.getText().toString().trim());
        student.setImel(imel);
        student.setClassId(classId);
        student.setMail(etStudentMail.getText().toString().trim());
        student.setPhone(etStudentPhone.getText().toString().trim());
        student.setSchoolId(schoolId);
        student.setClassPosition(etStudentPosition.getText().toString().trim());
        student.setPortraitUrl(portraitPath);
        if (cbStudentMale.isChecked()) {
            student.setSexual("男");
        } else {
            student.setSexual("女");
        }
        return student;
    }

    /**
     * 获取班级id
     *
     * @param className 班级名称
     * @param schoolId  学校id
     */
    public void getClassId(String className, String schoolId) {
        getClassIdSubscriber = new ProgressSubscriber<String>(getClassIdOnNextListener, SubmitStudentInfoActivity.this);
        mClassApi = new ClassApi(ApiService.getInstance(SubmitStudentInfoActivity.this).getmRetrofit());
        mClassApi.queryClassId(getClassIdSubscriber, className, schoolId);
    }

    /**
     * 初始化Retrofit 回调监听器
     */
    public void initListener() {
        //当是展示学生信息时初始化
        if (studentId != null) {
            getStudentInfoListener = new SubscriberOnNextListener<StudentLoginSuccessDto>() {
                @Override
                public void onNext(StudentLoginSuccessDto studentLoginSuccessDto) {
                    phone = studentLoginSuccessDto.getPhone();
                    initStudentInfo(studentLoginSuccessDto);
                }

                @Override
                public void onCompleted() {

                }
            };
        }
        //初始化提交信息的监听器
        else {
            mSubmitInfoNextListener = new SubscriberOnNextListener() {
                @Override
                public void onNext(Object o) {
                    Toast.makeText(SubmitStudentInfoActivity.this, R.string.submit_info_succeed, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SubmitStudentInfoActivity.this, SettingPasswordActivity.class);
                    intent.putExtra(Constants.PHONE, phone);
                    startActivity(intent);
                }

                @Override
                public void onCompleted() {

                }
            };

            //初始化获取班级id的回调监听器
            getClassIdOnNextListener = new SubscriberOnNextListener<String>() {
                @Override
                public void onNext(String classId) {
                    //班级存在的情况下
                    if (classId != null) {
                        SubmitStudentInfoActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(SubmitStudentInfoActivity.this, "测试toast", Toast.LENGTH_SHORT).show();
                                mStudent = initStudentInfo(classId);
                                submitInfoSubscriber = new ProgressSubscriber<String>(mSubmitInfoNextListener, SubmitStudentInfoActivity.this);
                                if (mStudent != null) {
                                    mStudentApi.submitStudentInfo(submitInfoSubscriber, mStudent);
                                }
                            }
                        });
                    }
                    //班级不存在，添加班级信息
                    else {
                        addClassSubscriber = new ProgressSubscriber<>(addClassSubscriberListener, SubmitStudentInfoActivity.this);
                        mClassApi.addClasses(addClassSubscriber, etStudentClass.getText().toString(), mSchoolId);
                    }

                }

                @Override
                public void onCompleted() {

                }
            };

            //初始化创建班级信息的回调监听器
            addClassSubscriberListener = new SubscriberOnNextListener<String>() {
                @Override
                public void onNext(String s) {
                    getClassId(etStudentClass.getText().toString(), schoolId);
                }

                @Override
                public void onCompleted() {

                }
            };
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (studentId == null)
            getMenuInflater().inflate(R.menu.storage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_menu_save) {
            CheckETEmptyUtil checkETEmptyUtil = new CheckETEmptyUtil(SubmitStudentInfoActivity.this);
            Student student = null;
            boolean result = checkETEmptyUtil.addView(etStudentName).addTip(R.string.name_cant_empty)
                    .addView(etStudentStudentNumber).addTip(R.string.student_number_cant_empty)
                    .addView(etStudentClass).addTip(R.string.class_cant_empty)
                    .addView(etStudentMail).addTip(R.string.mail_cant_empty)
                    .addView(etStudentPhone).addTip(R.string.smssdk_write_mobile_phone)
                    .addView(etStudentPosition).addTip(R.string.position_cant_empty).isEmpty();
            //头像地址是否为空
            if (portraitPath == null) {
                result = true;
                Toast.makeText(this, R.string.please_set_portrait, Toast.LENGTH_SHORT).show();
            }
            if (!result) {
                getClassId(etStudentClass.getText().toString(), schoolId);
            }
        }
        return false;
    }

    /**
     * 初始化view
     */
    public void initView() {
        etStudentSchool.setText(schoolName);
        etStudentSchool.setEnabled(false);
        etStudentPhone.setText(phone);
        etStudentPhone.setEnabled(false);
    }

    public void initStudentInfo(StudentLoginSuccessDto studentLoginSuccessDto) {
        etStudentName.setText(studentLoginSuccessDto.getName());
        if (studentLoginSuccessDto.getSexual().equals("男")) {
            cbStudentMale.setChecked(true);
        } else {
            cbStudentFemale.setChecked(true);
        }
        etStudentStudentNumber.setText(studentLoginSuccessDto.getStudentNumber());
        etStudentPosition.setText(studentLoginSuccessDto.getClassPosition());
        etStudentSchool.setText(studentLoginSuccessDto.getSchoolName());
        etStudentMail.setText(studentLoginSuccessDto.getMail());
        etStudentPhone.setText(studentLoginSuccessDto.getPhone());
        etStudentClass.setText(studentLoginSuccessDto.getClassName());
        //如果获取的头像路径不为空在显示头像
        if (studentLoginSuccessDto.getPortraitUrl() != null) {
            netWorkPortraitPath = ApiConfig.UPLOAD_PORTRAIT_PATH + studentLoginSuccessDto.getPortraitUrl();
            Glide.with(this).load(netWorkPortraitPath).into(ivStudentPortrait);
        }
        //设置不可编辑
        etStudentName.setEnabled(false);
        cbStudentFemale.setEnabled(false);
        cbStudentMale.setEnabled(false);
        etStudentStudentNumber.setEnabled(false);
        etStudentPosition.setEnabled(false);
        etStudentSchool.setEnabled(false);
        etStudentMail.setEnabled(false);
        etStudentPhone.setEnabled(false);
        etStudentClass.setEnabled(false);
    }

}
