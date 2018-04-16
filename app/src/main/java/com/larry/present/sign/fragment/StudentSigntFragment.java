package com.larry.present.sign.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.common.context.AppContext;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.DateUtil;
import com.larry.present.common.util.LocationUtil;
import com.larry.present.common.util.WifiSignUtil;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.sign.SignApi;
import com.larry.present.test.MyAnimation;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
*    
* 项目名称：present-android      
* 类描述： 学生开始签到fragment
* 创建人：Larry-sea   
* 创建时间：2017/4/24 20:47   
* 修改人：Larry-sea  
* 修改时间：2017/4/24 20:47   
* 修改备注：   
* @version    
*    
*/
public class StudentSigntFragment extends Fragment {

    //获取到的附近的wifi签到的签到课程id链表
    List<String> courseSignIdList;

    //监听学生签到回调
    SubscriberOnNextListener<String> subscriberOnNextListener;

    Button btnStudentSign;
    @BindView(R.id.iv_sign_defaullt)
    ImageView ivSignDefaullt;
    Unbinder unbinder;

    SignApi signApi;

    RxPermissions rxPermissions;

    @OnClick(R.id.btn_student_sign)
    void onClick(View view) {

        startSearchAnimation();


    }

    /**
     * 扫描wifi信号和签到
     */
    private void scanWifiAndSign() {

        //如果操作系统大于android 6.0
        if (Build.VERSION.SDK_INT >= 23) {
            boolean isOpened = LocationUtil.isOPen(getActivity());
            if (!isOpened) {
                Toast.makeText(getActivity(), R.string.pleast_opne_location, Toast.LENGTH_SHORT).show();
            }
        }
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE).subscribe(grant -> {
            if (grant) {
                //todo 学生签到逻辑
                courseSignIdList = WifiSignUtil.checkHasWifiHost(getActivity().getApplicationContext());
                if (courseSignIdList != null) {
                    signApi.studentSign(new ProgressSubscriber<String>(subscriberOnNextListener, getActivity()), courseSignIdList,
                            AccountManager.getStudent().getId(), String.valueOf(DateUtil.getDateTimeStap()));
                } else {
                    Toast.makeText(getActivity(), R.string.not_find_the_host_in_nearby, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), R.string.if_denied_you_will_cant_use_wifi_sign_feature, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_student_sign, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        AppContext.log(getClass().getSimpleName()+"            onCreateView     ");
        initData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initData() {
        signApi = new SignApi(ApiService.getInstance(getActivity()).getmRetrofit());
    }


    public void startSearchAnimation() {
        Animation anim = new MyAnimation(ivSignDefaullt, 150);
        anim.setDuration(3000);
        ivSignDefaullt.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scanWifiAndSign();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}
