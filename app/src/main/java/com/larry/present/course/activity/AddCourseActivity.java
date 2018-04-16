package com.larry.present.course.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.adapter.AddClassesAdapter;
import com.larry.present.bean.classes.Classes;
import com.larry.present.bean.course.Course;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.CheckETEmptyUtil;
import com.larry.present.common.util.DialogUtil;
import com.larry.present.common.util.DividerItemDecoration;
import com.larry.present.listener.RecyclerviewClickInterface;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.classes.ClassApi;
import com.larry.present.network.course.CourseApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：  老师添加课程activity
* 创建人：Larry-sea   
* 创建时间：2017/5/12 16:43   
* 修改人：Larry-sea  
* 修改时间：2017/5/12 16:43   
* 修改备注：   
* @version    
*    
*/
public class AddCourseActivity extends AppCompatActivity implements RecyclerviewClickInterface {

    CourseApi courseApi;
    ClassApi classApi;
    @BindView(R.id.toolbar_course_info)
    Toolbar toolbarSelectIdentity;
    @BindView(R.id.tv_add_course_name)
    EditText etAddCourseName;
    @BindView(R.id.btn_add_course_submit)
    Button btnAddCourseSubmit;
    @BindView(R.id.et_add_course_class_name)
    EditText etAddCourseClassName;
    @BindView(R.id.rv_add_course_class)
    RecyclerView rvAddCourseClass;

    SubscriberOnNextListener<Classes> classInfoListener;

    AddClassesAdapter addClassesAdapter;

    List<Classes> classesList;


    String courseId;
    @BindView(R.id.tv_add_course_add_btn)
    TextView tvAddCourseAddBtn;

    String courseName;

    CheckETEmptyUtil checkETEmptyUtil;


    //确认监听器
    DialogInterface.OnClickListener positiveClickListener;

    //取消监听器
    DialogInterface.OnClickListener negativeClickListener;

    AlertDialog alertDialog;


    //添加课程listener
    SubscriberOnNextListener<Course> addCourseListener;


    /**
     * 添加班级监听器
     */
    SubscriberOnNextListener<Classes> addClassesListener;


    SubscriberOnNextListener<String> addClassesToCourseListener;

    //需要添加的班级名
    String needAddedclassName;

    @OnClick(R.id.btn_add_course_submit)
    public void onClick(View view) {
        checkETEmptyUtil = new CheckETEmptyUtil(AddCourseActivity.this);
        boolean isEmpty = checkETEmptyUtil.addView(etAddCourseName).addTip(R.string.course_cant_empty)
                .addView(etAddCourseClassName).addTip(R.string.class_cant_empty).isEmpty();
        if (!isEmpty) {
            courseName = etAddCourseName.getText().toString().trim();
            courseApi.addCourse(new ProgressSubscriber<Course>(addCourseListener, AddCourseActivity.this), AccountManager.getTeacher().getId(), courseName);
        }

    }


    @OnClick(R.id.tv_add_course_add_btn)
    public void addClasses(View view) {
        //班级为空
        if (etAddCourseClassName.getText().toString().isEmpty()) {
            Toast.makeText(AddCourseActivity.this, R.string.class_cant_empty, Toast.LENGTH_SHORT).show();
        } else {
            needAddedclassName = etAddCourseClassName.getText().toString().trim();
            classApi.getClassesInfo(
                    new ProgressSubscriber<Classes>(classInfoListener, AddCourseActivity.this),
                    etAddCourseClassName.getText().toString().trim(),
                    AccountManager.getTeacher().getSchoolId());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();

    }

    public void initData() {
        courseApi = new CourseApi(ApiService.getInstance(AddCourseActivity.this).getmRetrofit());
        classApi = new ClassApi(ApiService.getInstance(AddCourseActivity.this).getmRetrofit());

    }

    public void initListener() {

        classInfoListener = new SubscriberOnNextListener<Classes>() {
            @Override
            public void onNext(Classes s) {
                //获取班级id
                if (s != null) {
                    if (classesList == null) {
                        classesList = new ArrayList<>();
                        classesList.add(s);
                        addClassesAdapter = new AddClassesAdapter(AddCourseActivity.this, classesList);
                        rvAddCourseClass.setLayoutManager(new LinearLayoutManager(AddCourseActivity.this));
                        rvAddCourseClass.addItemDecoration(new DividerItemDecoration(AddCourseActivity.this, DividerItemDecoration.VERTICAL_LIST));
                        rvAddCourseClass.setAdapter(addClassesAdapter);
                    } else {
                        classesList.add(s);
                        addClassesAdapter.setData(classesList);
                        addClassesAdapter.notifyDataSetChanged();
                    }

                } else {
                    initDialogClickListener();
                    alertDialog = DialogUtil.getDialog(AddCourseActivity.this, "添加班级", "是否添加班级" + needAddedclassName, true, true, positiveClickListener, negativeClickListener);
                }
                addClassesAdapter.setOnClickListener((RecyclerviewClickInterface) AddCourseActivity.this);
            }

            @Override
            public void onCompleted() {

            }
        };


        addCourseListener = new SubscriberOnNextListener<Course>() {
            @Override
            public void onNext(Course s) {
                courseId = s.getId();
                courseApi.addClassesToCourse(new ProgressSubscriber<String>(addClassesToCourseListener, AddCourseActivity.this), courseId, classesList);
            }

            @Override
            public void onCompleted() {

            }
        };


        addClassesToCourseListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(AddCourseActivity.this, R.string.course_add_success, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {

            }
        };

    }

    public void initView() {
        toolbarSelectIdentity.setTitle(R.string.add_course);
        toolbarSelectIdentity.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarSelectIdentity);
        toolbarSelectIdentity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onClick(View view, int position) {
        //删除不想添加的班级
        classesList.remove(position);
        addClassesAdapter.setData(classesList);
        addClassesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }


    /**
     * 初始化dialog 监听器
     */
    public void initDialogClickListener() {
        if (addClassesListener == null) {
            addClassesListener = new SubscriberOnNextListener<Classes>() {
                @Override
                public void onNext(Classes s) {
                    classesList.add(s);
                    addClassesAdapter.setData(classesList);
                    addClassesAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCompleted() {

                }
            };

            positiveClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    classApi.addClasses(new ProgressSubscriber<Classes>(addClassesListener, AddCourseActivity.this), needAddedclassName, AccountManager.getTeacher().getSchoolId());
                }
            };

            negativeClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            };
        }


    }


}
