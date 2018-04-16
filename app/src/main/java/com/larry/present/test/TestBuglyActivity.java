package com.larry.present.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.larry.present.R;
import com.tencent.bugly.crashreport.BuglyLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/4/19 9:49   
* 修改人：Larry-sea  
* 修改时间：2017/4/19 9:49   
* 修改备注：   
* @version    
*    
*/
public class TestBuglyActivity extends AppCompatActivity {
    @OnClick(R.id.button5)
    void onClick(View view) {
        BuglyLog.e("TestBuglyActivity", "测试结果，这个是普通日志");
        BuglyLog.i("TestBuglyActivity", "这个是测试不同级别的日志");
        try {
            throw new IllegalArgumentException("param cant empty");

        } catch (IllegalArgumentException e) {
            BuglyLog.e("TestBuglyActivity", "测试日志结果" + e.getMessage());
        }


    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_bugly);


        ButterKnife.bind(this);
    }


}
