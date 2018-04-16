package com.larry.present.sign.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.bean.classes.Classes;
import com.larry.present.bean.course.Course;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.DividerItemDecoration;
import com.larry.present.common.util.WifiAdmin;
import com.larry.present.common.util.WifiUtil;
import com.larry.present.config.Constants;
import com.larry.present.listener.onBackPressedClickListener;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.classes.ClassApi;
import com.larry.present.network.course.CourseApi;
import com.larry.present.network.sign.SignApi;
import com.larry.present.sign.activity.TeacherSignResultActivity;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/*
*    
* 项目名称：Present      
* 类描述：老师签到的fragment
* 创建人：Larry-sea   
* 创建时间：2017/4/5 11:24   
* 修改人：Larry-sea  
* 修改时间：2017/4/5 11:24   
* 修改备注：   
* @version    
*    
*/
public class TeacherSignFragment extends Fragment implements onBackPressedClickListener {


    SignApi signApi;

    CourseApi courseApi;

    ClassApi classApi;


    //监听所有课程listener
    SubscriberOnNextListener<List<Course>> getAllCourseListener;

    /**
     * 获取所有课程的listener
     */
    SubscriberOnNextListener<List<Classes>> getClassesListener;


    /**
     * 选择课程进行签到的监听器
     */
    SubscriberOnNextListener<String> selectCourseSignListener;

    /**
     * 选择班级签到的listener
     */
    SubscriberOnNextListener<String> selectClassSignListener;


    @BindView(R.id.viewStub_teacher_sign)
    ViewStub viewStubTeacherSign;

    @BindView(R.id.btn_teacher_stop_and__start_sign)
    Button startSignBtn;


    /**
     * 课程的recyclerview
     */
    RecyclerView courseRecyclerView;


    View rootView;


//    @BindView(R.id.iv_teacher_sign_icon)
//    ImageView ivTeacherSignIcon;

    CommonAdapter<Course> courseCommonAdapter;


    CommonAdapter<Classes> classesCommonAdapter;

    @OnClick(R.id.btn_teacher_stop_and__start_sign)
    public void startSign(View view) {
        courseApi.teacherGetAllCourse(new ProgressSubscriber<List<Course>>(getAllCourseListener, getActivity()), AccountManager.getTeacher().getId());
//        startActivity(new Intent(getActivity(), TeacherSignResultActivity.class));
    }

    Unbinder unbinder;


    /**
     * 课程签到发起id
     */
    private String courseStartSignId;


    /**
     * 选择的班级数组
     */
    private JSONArray classArray;


    /**
     * 选择班级进行签到的
     */
    private String selectClassSignId;


    HashSet<String> classSet;

    final static String TAG = TeacherSignFragment.class.toString();

    WifiAdmin wifiAdmin;

    //判断课程是否显示
    boolean isCourseShow = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_fragment_teacher_sign, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        initListener();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void initData() {
        signApi = new SignApi(ApiService.getInstance(getActivity()).getmRetrofit());
        courseApi = new CourseApi(ApiService.getInstance(getActivity()).getmRetrofit());
        classApi = new ClassApi(ApiService.getInstance(getActivity()).getmRetrofit());
        classSet = new LinkedHashSet<>(6);
        wifiAdmin = new WifiAdmin(getActivity());
    }


    public void initListener() {
        getAllCourseListener = new SubscriberOnNextListener<List<Course>>() {
            @Override
            public void onNext(List<Course> courseList) {
                viewStubTeacherSign.setVisibility(View.VISIBLE);
                // startSignBtn.setVisibility(View.INVISIBLE);
//                ivTeacherSignIcon.setVisibility(View.GONE);
                courseRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_bases);
                courseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                courseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                if (courseRecyclerView.getVisibility() == View.VISIBLE) {
                    initCourseAdapter(courseList);
                    courseRecyclerView.setAdapter(courseCommonAdapter);
                }
            }

            @Override
            public void onCompleted() {

            }
        };

        getClassesListener = new SubscriberOnNextListener<List<Classes>>() {
            @Override
            public void onNext(List<Classes> classes) {
                initClassesAdapter(classes);
                isCourseShow = false;
                courseRecyclerView.setAdapter(classesCommonAdapter);
            }

            @Override
            public void onCompleted() {
                // startSignBtn.setVisibility(View.VISIBLE);
                startSignBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        classArray = JSON.parseArray(JSON.toJSONString(classSet));
                        RxPermissions rxPermissions = new RxPermissions(getActivity());
                        rxPermissions.setLogging(true);
                        rxPermissions.request(Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.WRITE_SETTINGS).subscribe(granted -> {
                            if (granted) {
                                if (classSet.size() == 0) {
                                    Toast.makeText(getActivity(), R.string.must_choose_class, Toast.LENGTH_SHORT).show();
                                } else {
                                    signApi.selectClassToSign(new ProgressSubscriber<String>(selectClassSignListener, getActivity()), courseStartSignId, classArray);
                                }
                            } else {
                                Toast.makeText(getActivity(), R.string.if_denied_you_will_cant_use_wifi_sign_feature, Toast.LENGTH_SHORT).show();
                            }

                        });

                    }
                });
            }
        };


        selectCourseSignListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                if (s != null) {
                    courseStartSignId = s;
                }
            }

            @Override
            public void onCompleted() {

            }
        };


        selectClassSignListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                if (s != null) {
                    selectClassSignId = s;
                    String wifiName = "MD" + wifiAdmin.getLastThreMac() + courseStartSignId;
                    WifiUtil.openWifi(getActivity(), wifiName, Constants.WIFI_PASSWORD);
                    WifiUtil.isWifiApEnabled(getActivity());

                    Intent intent = new Intent(getActivity(), TeacherSignResultActivity.class);
                    intent.putExtra("courseSignId", courseStartSignId);
                    intent.putExtra("hiddenStopBtn", false);
                    startActivity(intent);

                }
            }

            @Override
            public void onCompleted() {

            }
        };

    }


    public void initCourseAdapter(final List<Course> courseList) {
        courseCommonAdapter = new CommonAdapter<Course>(getActivity(), R.layout.course_item, courseList) {
            @Override
            protected void convert(ViewHolder holder, Course course, int position) {
                holder.setText(R.id.tv_course_item_number, String.valueOf(position));
                holder.setText(R.id.tv_course_item_name, course.getCourseName() == null ? "" : course.getCourseName());
                holder.setOnClickListener(R.id.ll_course_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, String.valueOf(position), Toast.LENGTH_SHORT).show();
                        signApi.selectCourseToSign(new ProgressSubscriber<String>(selectCourseSignListener, getActivity()), AccountManager.getTeacher().getId(), course.getId(), Constants.WIFI_SIGN, 2);
                        classApi.getClassesUnderCourse(new ProgressSubscriber<List<Classes>>(getClassesListener, getActivity()), AccountManager.getTeacher().getId(), course.getId());
                    }
                });
            }
        };
    }


    public void initClassesAdapter(final List<Classes> classesList) {
        for (int i = 0; i < classesList.size(); i++) {
            classSet.add(classesList.get(i).getId());
        }
        classesCommonAdapter = new CommonAdapter<Classes>(getActivity(), R.layout.classes_item, classesList) {
            @Override
            protected void convert(ViewHolder holder, Classes classes, int position) {
                holder.setText(R.id.tv_classes_item_number, String.valueOf(position + 1));
                holder.setText(R.id.tv_classes_item_name, classes.getClassName());
                holder.setOnClickListener(R.id.cb_classes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (classSet.contains(classes.getId())) {
                            classSet.remove(classes.getId());
                        } else {
                            classSet.add(classes.getId());
                        }
                    }
                });
            }
        };
    }


    @Override
    public boolean onBackPressed(int keyCode, KeyEvent event) {
        if (!isCourseShow) {
            courseRecyclerView.setAdapter(courseCommonAdapter);
            isCourseShow = true;
            return true;
        } else {
            return false;
        }
    }
}

