package com.larry.present.test;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.larry.present.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/5/10 15:18   
* 修改人：Larry-sea  
* 修改时间：2017/5/10 15:18   
* 修改备注：   
* @version    
*    
*/
public class TestCallPhonePermission extends AppCompatActivity {


    @OnClick(R.id.button)
    public void onClick(View view) {
        int permissiontCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
       /* if (permissiontCheck == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "已经授权了", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);

            Toast.makeText(this, "没有授权", Toast.LENGTH_SHORT).show();
        }*/
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(grant ->
        {
            if (grant) {
                Toast.makeText(this, "已经授权了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "没有授权", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_rxjava_activity);
        ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
