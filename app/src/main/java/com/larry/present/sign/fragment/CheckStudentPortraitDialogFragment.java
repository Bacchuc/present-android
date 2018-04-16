package com.larry.present.sign.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.larry.present.R;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.loginregister.dto.StudentLoginSuccessDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.student.StudentApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
*    
* 项目名称：present-android      
* 类描述： 查看学生大头像的activity
* 创建人：Larry-sea   
* 创建时间：2017/5/19 11:34   
* 修改人：Larry-sea  
* 修改时间：2017/5/19 11:34   
* 修改备注：   
* @version    
*    
*/
public class CheckStudentPortraitDialogFragment extends DialogFragment {

    @BindView(R.id.iv_check_student_portrait)
    ImageView ivCheckStudentPortrait;
    Unbinder unbinder;

    StudentApi studentApi;

    SubscriberOnNextListener<StudentLoginSuccessDto> getPortraitListener;

    String studentId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_student_portrait, container, false);
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
        getPortraitListener = new SubscriberOnNextListener<StudentLoginSuccessDto>() {
            @Override
            public void onNext(StudentLoginSuccessDto studentLoginSuccessDto) {
                Glide.with(getActivity()).load(studentLoginSuccessDto.getPortraitUrl()).placeholder(R.drawable.smssdk_cp_default_avatar).into(ivCheckStudentPortrait);
            }

            @Override
            public void onCompleted() {

            }
        };
        studentId = getArguments().getString("studentId");
        studentApi = new StudentApi(ApiService.getInstance(getActivity()).getmRetrofit());
        studentApi.getStudentInfo(new ProgressSubscriber<StudentLoginSuccessDto>(getPortraitListener, getActivity()), studentId);
    }


}
