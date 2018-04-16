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
import com.larry.present.network.password.ChangePasswordApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
*    
* 项目名称：present-android      
* 类描述：  用户修改密码
* 创建人：Larry-sea   
* 创建时间：2017/5/20 17:01   
* 修改人：Larry-sea  
* 修改时间：2017/5/20 17:01   
* 修改备注：   
* @version    
*    
*/
public class ForgetPasswordActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_change_pwd)
    Toolbar toolbarChangePwd;
    @BindView(R.id.et_change_pwd_sure_pwd)
    EditText etChangePwdSurePwd;
    @BindView(R.id.et_change_pwd_input_pwd)
    EditText etChangePwdInputPwd;

    CheckETEmptyUtil mCheckEmptyUtil;

    SubscriberOnNextListener<String> forgetPwdOnNextListener;

    ChangePasswordApi changePasswordApi;

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
        toolbarChangePwd.setTitle("修改密码");
        toolbarChangePwd.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbarChangePwd.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.id_menu_save) {
                    changePwd();
                } else if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                }
                return true;
            }
        });
        setSupportActionBar(toolbarChangePwd);
    }

    public void initListener() {
        forgetPwdOnNextListener=new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(ForgetPasswordActivity.this, R.string.password_change_succeed, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.id_menu_save) {
//            changePwd();
//        } else if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//        }
//        return true;
//    }

    /**
     * 修改密码
     */
    public void changePwd() {
        if (changePasswordApi == null) {
            changePasswordApi = new ChangePasswordApi(ApiService.getInstance(this).getmRetrofit());
        }
        if (getPassword() != null) {
            changePasswordApi.forgetPassword(new ProgressSubscriber<String>(forgetPwdOnNextListener, this), phone,getPassword());
        }
    }

    /**
     * 获取设置以后的密码
     *
     * @return 返回密码
     */
    public String getPassword() {
        mCheckEmptyUtil = new CheckETEmptyUtil(ForgetPasswordActivity.this);
        boolean isEmpty = mCheckEmptyUtil.addView(etChangePwdInputPwd).addTip(R.string.password_cant_empty).
                addView(etChangePwdSurePwd).addTip(R.string.password_cant_empty).isEmpty();
        if (!etChangePwdInputPwd.getText().toString().trim().equals(etChangePwdSurePwd.getText().toString().trim())) {
            Toast.makeText(this, R.string.password_not_same, Toast.LENGTH_SHORT).show();
            return null;
        } else {
            //将密码经过md5 加密,这里如果想要再提高安全性防止暴力破解可以再加盐，防止暴力破解
            return MD5EncipherUtil.md5(etChangePwdInputPwd.getText().toString().trim());
        }
    }
}
