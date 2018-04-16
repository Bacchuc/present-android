package com.larry.present.loginregister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.CheckETEmptyUtil;
import com.larry.present.common.util.MD5EncipherUtil;
import com.larry.present.config.Constants;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.password.SettingPasswordApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Laiyin on 2017/8/20.
 */

public class SettingPasswordActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_change_pwd)
    Toolbar toolbarSettingPwd;
    @BindView(R.id.et_change_pwd_sure_pwd)
    EditText etSettingPwdSurePwd;
    @BindView(R.id.et_change_pwd_input_pwd)
    EditText etSettingPwdInputPwd;

    CheckETEmptyUtil mCheckEmptyUtil;

    SubscriberOnNextListener<String> settingPwdOnNextListener;

    SettingPasswordApi settingPasswordApi;

    String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra(Constants.PHONE);
        initToolbar();
        initListener();
    }

    public void initToolbar() {
        toolbarSettingPwd.setTitle("设置密码");
        setSupportActionBar(toolbarSettingPwd);
//        toolbarSettingPwd.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbarSettingPwd.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.id_menu_save) {
                    settingPwd();
                }
                return true;
            }
        });
        //点击返回后会重新提交信息，个人信息会重复，所以在这个问题没解决之前无返回功能
//        toolbarSettingPwd.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

    }

    public void initListener() {
        settingPwdOnNextListener=new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(SettingPasswordActivity.this, R.string.password_setting_succeed, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCompleted() {

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_menu, menu);
        return true;
    }

    /**
     * 设置密码
     */
    public void settingPwd() {
        if (settingPasswordApi == null) {
            settingPasswordApi = new SettingPasswordApi(ApiService.getInstance(this).getmRetrofit());
        }

        if (getPassword() != null) {
            settingPasswordApi.settingPassword(new ProgressSubscriber<String>(settingPwdOnNextListener, this), phone, getPassword());
        }
    }

    /**
     * 获取设置以后的密码
     *
     * @return 返回密码
     */
    public String getPassword() {
        mCheckEmptyUtil = new CheckETEmptyUtil(SettingPasswordActivity.this);
        boolean isEmpty = mCheckEmptyUtil.addView(etSettingPwdInputPwd).addTip(R.string.password_cant_empty).
                addView(etSettingPwdSurePwd).addTip(R.string.password_cant_empty).isEmpty();
        if (!etSettingPwdInputPwd.getText().toString().trim().equals(etSettingPwdSurePwd.getText().toString().trim())) {
            Toast.makeText(this, R.string.password_not_same, Toast.LENGTH_SHORT).show();
            return null;
        } else {
            //将密码经过md5 加密,这里如果想要再提高安全性防止暴力破解可以再加盐，防止暴力破解
            return MD5EncipherUtil.md5(etSettingPwdInputPwd.getText().toString().trim());
        }
    }
}
