package com.larry.present.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
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
* 类描述： 修改密码的activity
* 创建人：Larry-sea   
* 创建时间：2017/5/26 14:40   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 14:40   
* 修改备注：   
* @version    
*    
*/
public class ChangePasswordActivity extends AppCompatActivity {

    ChangePasswordApi changePasswordApi;

    @BindView(R.id.et_change_pwd_input_pwd)
    EditText firstPwd;

    @BindView(R.id.et_change_pwd_sure_pwd)
    EditText surePwd;

    @BindView(R.id.toolbar_change_pwd)
    Toolbar toolbar;

    SubscriberOnNextListener<String> subscriberOnNextListener;

    CheckETEmptyUtil checkETEmptyUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        initToolbar();
        initListener();
    }


    public void initToolbar() {
        toolbar.setTitle(R.string.change_password);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_menu_save) {
            changePassword();
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    /**
     * 修改密码并且提交到服务器
     */
    private void changePassword() {
        if (changePasswordApi == null) {
            changePasswordApi = new ChangePasswordApi(ApiService.getInstance(this).getmRetrofit());
        }
        int userType = AccountManager.getStudent() == null ? Constants.TEACHER_TYPE : Constants.STUDENT_TYPE;
        String userId = AccountManager.getStudent() == null ? AccountManager.getTeacher().getId() : AccountManager.getStudent().getId();
        String password = getPassword();
        if (password != null) {
            changePasswordApi.changePassword(new ProgressSubscriber<String>(subscriberOnNextListener, this), String.valueOf(userType), userId, password);
        }
    }

    public void initListener() {
        subscriberOnNextListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(ChangePasswordActivity.this, R.string.password_change_succeed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {

            }
        };
    }


    /**
     * 获取设置以后的密码
     *
     * @return 返回密码
     */
    public String getPassword() {
        checkETEmptyUtil = new CheckETEmptyUtil(ChangePasswordActivity.this);
        boolean isEmpty = checkETEmptyUtil.addView(firstPwd).addTip(R.string.password_cant_empty).
                addView(surePwd).addTip(R.string.password_cant_empty).isEmpty();
        if (!firstPwd.getText().toString().trim().equals(surePwd.getText().toString().trim())) {
            Toast.makeText(this, R.string.password_not_same, Toast.LENGTH_SHORT).show();
            return null;
        } else {
            //将密码经过md5 加密,这里如果想要再提高安全性防止暴力破解可以再加盐，防止暴力破解
            return MD5EncipherUtil.md5(firstPwd.getText().toString().trim());
        }
    }

}
