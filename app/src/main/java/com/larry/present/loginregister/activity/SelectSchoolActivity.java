package com.larry.present.loginregister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.larry.present.R;
import com.larry.present.bean.school.School;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.KeyBoardUtil;
import com.larry.present.config.Constants;
import com.larry.present.loginregister.adapter.BaseFilterAdapter;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.schoolapi.GetSchoolApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Larry-sea on 2016/11/24.
 * <p>
 * 当老师注册选择学校的activity
 */
public class SelectSchoolActivity extends AppCompatActivity {

    final static String TAG = SelectSchoolActivity.class.toString();
    @BindView(R.id.toolbar_course_info)
    Toolbar toolbarSelectSchoole;
    //学校校名的autoTextVie
    @BindView(R.id.at_slect_school_name)
    AutoCompleteTextView atSlectSchoolName;

    //适配器
    private BaseFilterAdapter mAdapter;
    List<String> mSchoolNameList;
    //学校名称的hashmap
    HashMap<String, String> mSchooleNameMap;
    //进度订阅者
    ProgressSubscriber<List<School>> progressSubscriber;
    //监听器
    SubscriberOnNextListener<List<School>> subscriberOnNextListener;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_school);
        ButterKnife.bind(this);
        initData();
        initToolbar();
        phone = getIntent().getStringExtra(Constants.PHONE);

    }


    /*
    * 初始化数据
    *
    * */
    public void initData() {


        mSchoolNameList = new ArrayList<String>();
        subscriberOnNextListener = new SubscriberOnNextListener<List<School>>() {
            @Override
            public void onCompleted() {
                //数据接收完毕等地用户输入数据
                KeyBoardUtil.openKeybord(atSlectSchoolName, SelectSchoolActivity.this);
                //设置autoTextCompleteView的item点击事件
                atSlectSchoolName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if (mSchooleNameMap != null) {
                            Intent registerIntent = new Intent(SelectSchoolActivity.this, SelectIdentityActivity.class);
                            registerIntent.putExtra(Constants.SCHOOLE_NAME, (String) mAdapter.getItem(position));
                            registerIntent.putExtra(Constants.SCHOOL_ID, mSchooleNameMap.get(mAdapter.getItem(position)));
                            registerIntent.putExtra(Constants.PHONE, phone);
                            startActivity(registerIntent);
                        }
                    }
                });

            }

            @Override
            public void onNext(List<School> colleges) {
                if (colleges != null) {
                    getSchooleNameList(colleges);
                    collegeListToHashMap(colleges);
                    mAdapter = new BaseFilterAdapter(SelectSchoolActivity.this, new ArrayList<>(mSchoolNameList));
                    atSlectSchoolName.setAdapter(mAdapter);
                }

            }
        };
        progressSubscriber = new ProgressSubscriber<List<School>>(subscriberOnNextListener, SelectSchoolActivity.this);

        GetSchoolApi getSchoolApi = new GetSchoolApi(ApiService.getInstance(SelectSchoolActivity.this).getmRetrofit());


        getSchoolApi.getAllSchool(progressSubscriber, phone);

    }


    /**
     * 获取学校姓名的list
     *
     * @param schoolNameList
     */
    public void getSchooleNameList(List<School> schoolNameList) {
        for (School college : schoolNameList) {
            mSchoolNameList.add(college.getSchoolName());
        }
    }


    /**
     * 将college list 转换为hashmap
     *
     * @param collegeList
     */
    public void collegeListToHashMap(List<School> collegeList) {
        if (mSchooleNameMap == null) {
            mSchooleNameMap = new HashMap<String, String>();
        }
        for (int position = 0; position < collegeList.size(); position++) {
            mSchooleNameMap.put(collegeList.get(position).getSchoolName(), collegeList.get(position).getSchoolId());
        }
    }


    /*
    * 初始化view
    *
    * */
    public void initToolbar() {
        toolbarSelectSchoole.setTitle(R.string.select_schoole);
        toolbarSelectSchoole.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);

        setSupportActionBar(toolbarSelectSchoole);
        toolbarSelectSchoole.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    /*
    * 销毁比较占用内存的变量
    *
    * */
    @Override
    public void onStop() {
        super.onStop();
        if (mSchooleNameMap != null) {
            mSchooleNameMap.clear();
            mSchooleNameMap = null;
        }
        if (mSchoolNameList != null) {
            mSchoolNameList.clear();
            mSchoolNameList = null;
        }
    }


}
