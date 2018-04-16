package com.larry.present.sign.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larry.present.R;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.config.Constants;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.sign.SignApi;
import com.larry.present.sign.activity.TeacherSignResultActivity;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
*    
* 项目名称：present-android      
* 类描述：  修改学生签到状态的dialog
* 创建人：Larry-sea   
* 创建时间：2017/5/21 15:18   
* 修改人：Larry-sea  
* 修改时间：2017/5/21 15:18   
* 修改备注：   
* @version    
*    
*/
public class ChangeStudentSignStateDialog extends DialogFragment {


    @OnClick(R.id.btn_dialog_sign_sure)
    public void sure(View view) {
        signApi.changeStudentSign(new ProgressSubscriber<String>(changeStudentSignStateListener, getActivity()), courseSignId, studentId, signType);
    }


    @OnClick(R.id.btn_dialog_sign_cancel)
    public void cancel(View view) {
        this.dismiss();
    }

    //签到状态
    String signType;
    Unbinder unbinder;

    //切换学生为签到状态
    @OnCheckedChanged(R.id.cb_dialog_sign)
    void signChange(boolean isChecked) {
        if (isChecked) {
            signType = Constants.STUDENT_SIGN;
        }
    }


    //切换学生为请假状态
    @OnCheckedChanged(R.id.cb_dialog_sick_leave)
    void sickLeaveChange(boolean isChecked) {
        if (isChecked) {
            signType = Constants.STUDENT_SIGN;
        }
    }

    //切换学生为缺勤的状态
    @OnCheckedChanged(R.id.cb_dialog_absence)
    void absenceChange(boolean isChecked) {
        if (isChecked) {
            signType = Constants.STUDENT_ABSENCE;
        }

    }

    SignApi signApi;

    //改变学生状态的listener
    SubscriberOnNextListener<String> changeStudentSignStateListener;

    String courseSignId;
    String studentId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_change_student_sign_state, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void initView() {
        //设置签到的默认状态为签到
        signType = Constants.STUDENT_SIGN;
        courseSignId = getArguments().getString("courseSignId");
        studentId = getArguments().getString("studentId");
        signApi = new SignApi(ApiService.getInstance(getActivity()).getmRetrofit());
        changeStudentSignStateListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                //通知teacherSignResultActivity 刷新结果
                ((TeacherSignResultActivity) getActivity()).changeStudentSignState(signType);
            }

            @Override
            public void onCompleted() {

            }
        };


    }

}
