package com.larry.present.sign.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.bean.classes.Classes;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.DialogUtil;
import com.larry.present.common.util.DividerItemDecoration;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.classes.ClassApi;
import com.larry.present.network.sign.SignApi;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
*    
* 项目名称：present-android      
* 类描述：  选择班级导出这门课程的所有签到记录
* 创建人：Larry-sea   
* 创建时间：2017/5/27 17:09   
* 修改人：Larry-sea  
* 修改时间：2017/5/27 17:09   
* 修改备注：   
* @version    
*    
*/
public class SelectClassActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_select_class)
    Toolbar toolbarSelectClass;
    @BindView(R.id.rv_bases)
    RecyclerView rvBases;

    ClassApi classApi;

    //课程id
    String courseId;

    SubscriberOnNextListener<List<Classes>> getClassListener;

    //确认按钮的监听器
    Dialog.OnClickListener sureOnClickListener;

    //取消按钮的监听器
    Dialog.OnClickListener cancelOnClickListener;

    //签到api
    SignApi signApi;

    //发送邮件的监听器
    SubscriberOnNextListener<String> sendMailListener;

    //记录选择的班级id
    String classId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);
        ButterKnife.bind(this);
        initToolbar();
        initDialogInterface();
        initListener();
        initData();


    }


    //初始化toolbar
    public void initToolbar() {
        toolbarSelectClass.setTitle(R.string.select_class);
        toolbarSelectClass.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbarSelectClass);
        toolbarSelectClass.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    //初始化监听器
    public void initListener() {
        getClassListener = new SubscriberOnNextListener<List<Classes>>() {
            @Override
            public void onNext(List<Classes> classesList) {
                rvBases.setLayoutManager(new LinearLayoutManager(SelectClassActivity.this));
                rvBases.addItemDecoration(new DividerItemDecoration(SelectClassActivity.this, DividerItemDecoration.VERTICAL_LIST));
                rvBases.setAdapter(new CommonAdapter<Classes>(SelectClassActivity.this, R.layout.classes_item, classesList) {
                    @Override
                    protected void convert(ViewHolder holder, Classes classes, int position) {
                        holder.setText(R.id.tv_classes_item_number, String.valueOf(position + 1));
                        holder.setText(R.id.tv_classes_item_name, classes.getClassName());
                        holder.getView(R.id.cb_classes).setVisibility(View.GONE);
                        holder.setOnClickListener(R.id.ll_classes_item, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                classId = classes.getId();
                                DialogUtil.getDialog(SelectClassActivity.this, getResources().getString(R.string.are_you_sure_send_email),
                                        "确认发送" + classes.getClassName() + "班的考勤信息吗", true, true, sureOnClickListener, cancelOnClickListener);
                            }
                        });
                    }
                });
            }

            @Override
            public void onCompleted() {

            }
        };

    }

    //初始化数据
    public void initData() {
        courseId = getIntent().getStringExtra("courseId");
        classApi = new ClassApi(ApiService.getInstance(this).getmRetrofit());
        classApi.getClassesUnderCourse(new ProgressSubscriber<>(getClassListener, this), AccountManager.getTeacher().getId(), courseId);

    }


    /*
    * 初始化对话框的监听器
    *
    * */
    public void initDialogInterface() {
        sureOnClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (signApi == null) {
                    signApi = new SignApi(ApiService.getInstance(SelectClassActivity.this).getmRetrofit());
                }
                signApi.sendSignMail(new ProgressSubscriber<String>(sendMailListener, SelectClassActivity.this), AccountManager.getTeacher().getId(), courseId, classId, AccountManager.getTeacher().getMail());
            }
        };

        cancelOnClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

    }

}
