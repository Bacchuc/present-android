package com.larry.present.logo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bacchus.androidutillibrary.util.DownloadThreadUtil;
import com.larry.present.R;
import com.larry.present.common.context.AppContext;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.DialogUtil;
import com.larry.present.common.util.GetVersionUtil;
import com.larry.present.common.util.ToastUtil;
import com.larry.present.config.Constants;
import com.larry.present.loginregister.activity.LoginActivity;
import com.larry.present.logo.dto.IsNeedToUpdateDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.logo.UpdateApi;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.larry.present.config.Constants.filePath;
import static com.larry.present.config.Constants.filePathApk;
import static com.larry.present.config.Constants.filePathApkName;
import static com.larry.present.config.Constants.userInfoFileName;

/**
 * Created by Laiyin on 2017/8/3.
 * <p>
 * Logo页面 此页面中进行更新检查和登陆状态检查 先进行更新检查
 */

public class LogoActivity extends AppCompatActivity implements DownloadThreadUtil.ThreadCallback {

    SubscriberOnNextListener<IsNeedToUpdateDto> checkAppUpdateListener;
    UpdateApi updateApi;
    private int downloadApkSize;
    private int fileApkSize;

    @BindView(R.id.tv_check_update)
    TextView tvCheckUpdate;
//    @BindView(R.id.bg)
//    ImageView bg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        initListener();
        checkUpdate();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        Glide.with(this)
//                .load(R.drawable.logo_activity_background)
//                .override(getResources().getDimensionPixelSize(R.dimen.img_width), getResources().getDimensionPixelSize(R.dimen.img_height))
//                .into(bg);
    }

    public void initListener() {
        checkAppUpdateListener = new SubscriberOnNextListener<IsNeedToUpdateDto>() {
            @Override
            public void onNext(IsNeedToUpdateDto s) {
                //有新版本，既当前版本需升级
                if (s.getHasNewVersion() == 1) {
                    //需要强制升级
                    AppContext.log(Thread.currentThread().getName());
                    if (s.getMust() == 1) {
                        textViewGone();
                        ToastUtil.showShort(LogoActivity.this, "当前版本过低，需要更新！请等待！");
                        new DownloadThreadUtil(LogoActivity.this, LogoActivity.this, s.getUpdateUrl(), filePath, filePathApk, filePathApkName).start();
                    }else {
                        //无需强制升级
                        textViewGone();
                        doNewVersionUpdate(s.getVersionCode(), s.getVersionName(), s.getUpdateUrl());
                    }
                }else {
                    //无需升级 直接检查登陆状态
                    autoLogin();
                }
            }

            @Override
            public void onCompleted() {
                textViewGone();
            }
        };
    }

    /**
     * 非强制更新 更新新版本
     *
     * @param verCode 新版本的版本号
     * @param verName 新版本的版本名
     */
    private void doNewVersionUpdate(int verCode, String verName, String url) {
        String str = "当前版本："
                + GetVersionUtil.getPackageVersionName(this)
                + " Code:"
                + GetVersionUtil.getPackageVersionCode(this)
                + " ,发现新版本："
                + verName
                + " Code:"
                + verCode
                + " ,是否更新？";
        DialogUtil.getDialog(this, "软件更新", str, true, true, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                DialogUtil.getProgressDialog(LogoActivity.this, "正在下载", "请稍候");
                new DownloadThreadUtil(LogoActivity.this, LogoActivity.this, Constants.downloadApkUrl, filePath, filePathApk, filePathApkName).start();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击"取消"按钮之后退出程序
                dialog.dismiss();
                //无需升级 直接检查登陆状态(如果已经登陆了则自动登陆否则跳到登陆页面)
                autoLogin();
            }
        });
    }


    /**
     * 检查更新网络异常
     */
    public void netError() {
        tvCheckUpdate.setText("网络异常，请检查网络链接");
    }

    /**
     * 隐藏检查更新的textview提示
     */
    public void textViewGone() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCheckUpdate.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 检查系统是否需要更新
     */
    public void checkUpdate() {
        if (updateApi == null) {
            updateApi = new UpdateApi(ApiService.getInstance(this).getmRetrofit());
        }
        AppContext.log("LogoActivity----------------" + GetVersionUtil.getPackageVersionName(this));
        updateApi.isNeedToUpdate(new ProgressSubscriber<IsNeedToUpdateDto>(checkAppUpdateListener, this),
                GetVersionUtil.getPackageVersionName(this));
    }

    @Override
    public void threadStartListener() {
        new Handler(LogoActivity.this.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                tvCheckUpdate.setText("下载中，请稍后...");
            }
        });
    }

    @Override
    public void threadDownloadListener(int i, int i1) {
        downloadApkSize = i;
        fileApkSize = i1;
        new Handler(LogoActivity.this.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                tvCheckUpdate.setText("已下载" + Integer.valueOf(downloadApkSize * 100 / fileApkSize).toString() + "%");
            }
        });
    }

    @Override
    public void threadEndListener() {

    }

    /**
     * 自动登陆
     */
    public void autoLogin() {
        SharedPreferences sp = getSharedPreferences(userInfoFileName, MODE_PRIVATE);
        if (sp.getAll() != null) {
            for (String s : sp.getAll().keySet()) {
                AppContext.log("值得类型      " + sp.getAll().get(s).getClass().getSimpleName());
                AppContext.log(s + "   K-V    " + sp.getAll().get(s) + "\n");
            }
        }
        Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
        if (sp.contains(Constants.PHONE)) {
            AppContext.log("----------------已经存过----------");
            intent.putExtra(LoginActivity.REGISTER, true);
        } else {
            AppContext.log("--------------没有存过---------------");
        }
        startActivity(intent);
        finish();
    }

}
