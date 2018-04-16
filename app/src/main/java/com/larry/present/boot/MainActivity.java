package com.larry.present.boot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.boot.adapter.BaseFragmentPagerAdapter;
import com.larry.present.course.activity.AddCourseActivity;
import com.larry.present.listener.onBackPressedClickListener;
import com.larry.present.setting.activity.SettingActivity;
import com.larry.present.sign.fragment.StudentCheckCourseFragment;
import com.larry.present.sign.fragment.StudentSigntFragment;
import com.larry.present.sign.fragment.TeacherCheckSignFragment;
import com.larry.present.sign.fragment.TeacherSignFragment;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.app_bar_main_tl)
    TabLayout appBarMainTl;

    @BindView(R.id.activity_main_viewpager_vp)
    ViewPager activityMainViewpagerVp;

    FragmentPagerAdapter mFragmentPagerAdapter;

    /**
     * fragment集合
     */
    List<Fragment> mFragmentList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RxPermissions rxPermissions;

    int pagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initConfig();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void initView() {
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentList = new ArrayList<Fragment>();
        //如果当前是学生实例登录存在
        if (AccountManager.getStudent() != null) {
            mFragmentList.add(new StudentSigntFragment());
            mFragmentList.add(new StudentCheckCourseFragment());
        }
        //如果当前是老师实例登录则添加老师的fragment
        else {
            mFragmentList.add(new TeacherSignFragment());
            mFragmentList.add(new TeacherCheckSignFragment());
        }
        mFragmentPagerAdapter = new BaseFragmentPagerAdapter(MainActivity.this, fragmentManager, mFragmentList);
        activityMainViewpagerVp.setAdapter(mFragmentPagerAdapter);
        appBarMainTl.setupWithViewPager(activityMainViewpagerVp);
    }


    /**
     * 应用启动的一些配置属性
     */
    public void initConfig() {
    }

    public void showClassFragment() {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            pagePosition = activityMainViewpagerVp.getCurrentItem();
            if (pagePosition == 0) {
                //通知第一个fragment的返回
                ((onBackPressedClickListener) mFragmentList.get(0)).onBackPressed(keyCode, event);
            } else {
                //通知第二个fragment的返回
                ((onBackPressedClickListener) mFragmentList.get(1)).onBackPressed(keyCode, event);
            }
            Toast.makeText(this, "返回了", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (AccountManager.getTeacher() != null) {
            getMenuInflater().inflate(R.menu.teacher_menu, menu);
        } else if (AccountManager.getStudent() != null) {
            getMenuInflater().inflate(R.menu.student_menu, menu);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //老师添加课程
            case R.id.id_menu_teacher_add_course:
                startActivity(new Intent(this, AddCourseActivity.class));
                break;
            //打开设置
            case R.id.id_menu_teacher_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.id_menu_student_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
