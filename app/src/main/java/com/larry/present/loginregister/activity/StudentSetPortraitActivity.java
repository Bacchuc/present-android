package com.larry.present.loginregister.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.larry.present.R;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.DateUtil;
import com.larry.present.config.Constants;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.student.StudentApi;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述： 学生设置头像接口
* 创建人：Larry-sea   
* 创建时间：2017/4/19 14:54   
* 修改人：Larry-sea  
* 修改时间：2017/4/19 14:54   
* 修改备注：   
* @version    
*    
*/
public class StudentSetPortraitActivity extends AppCompatActivity {


    @BindView(R.id.iv_portrait_portrait)
    ImageView ivPortraitPortrait;

    final static String TAG = StudentSetPortraitActivity.class.toString();

    final String portraitRootPath = Environment.getExternalStorageDirectory() + "/here";

    //头像地址
    String portraitPath;

    @BindView(R.id.btn_portrait_take_picture)
    Button takePicture;

    //调用拍照结果结果以后
    boolean resultAfter = false;

    StudentApi studentApi;

    SubscriberOnNextListener<String> studentUploadListener;

    //手机号
    String phone;

    //头像
    File portrait;

    //头像存储路径
    String serverPortraitPath;

    StringBuilder stringBuilder;

    @OnClick(R.id.btn_portrait_take_picture)
    void takePicture(View view) {
        if (resultAfter) {
            studentApi.studentUploadPortrait(new ProgressSubscriber<String>(studentUploadListener, this), phone, portrait);
            Toast.makeText(this, R.string.uploading_portrait, Toast.LENGTH_SHORT).show();
        } else {
            startCameraByIntent();
        }
    }

    private void startCameraByIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new File(portraitRootPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        stringBuilder.append(portraitRootPath);
        stringBuilder.append('/');
        stringBuilder.append(phone);
        stringBuilder.append("portrait.jpg");
        portraitPath = stringBuilder.toString();

        File portraitFile = new File(portraitPath);
        Uri photoUri = Uri.fromFile(portraitFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_set_portrait);
        ButterKnife.bind(this);
        initData();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //验证头像文件是否存在
        portrait = new File(portraitPath);
        if (requestCode == 2 && resultCode == -1) {
            if (portrait.exists()) {
                Glide.with(this).load(portraitPath).signature(new StringSignature(DateUtil.getDate().toString())).into(ivPortraitPortrait);
                takePicture.setText("上传");
                resultAfter = true;
            }
        }
    }


    public void initData() {
        stringBuilder = new StringBuilder();
        phone = getIntent().getStringExtra(Constants.PHONE);
        studentApi = new StudentApi(ApiService.getInstance(this).getmRetrofit());
        studentUploadListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                serverPortraitPath = s;
                Toast.makeText(StudentSetPortraitActivity.this, R.string.upload_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra(Constants.PORTRAITPATH, serverPortraitPath);
                setResult(-1, intent);
                finish();
            }

            @Override
            public void onCompleted() {

            }
        };

    }
}
