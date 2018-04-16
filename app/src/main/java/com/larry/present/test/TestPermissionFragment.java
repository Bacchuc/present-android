package com.larry.present.test;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.common.util.WifiUtil;
import com.larry.present.config.Constants;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/5/8 21:24   
* 修改人：Larry-sea  
* 修改时间：2017/5/8 21:24   
* 修改备注：   
* @version    
*    
*/
public class TestPermissionFragment extends AppCompatActivity {
    @OnClick(R.id.button)
    void onclick(View view) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        rxPermissions.request(Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.CHANGE_WIFI_STATE).subscribe(granted -> {
            if (granted) {
                WifiUtil.openWifi(this, "fasfasdfasdfafd", Constants.WIFI_PASSWORD);
                WifiUtil.isWifiApEnabled(this);

            } else {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }

        });

    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_rxjava_activity);
        ButterKnife.bind(this);
    }
}
