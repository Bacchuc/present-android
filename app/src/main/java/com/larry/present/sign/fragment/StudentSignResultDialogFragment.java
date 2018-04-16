package com.larry.present.sign.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.larry.present.R;
import com.larry.present.config.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
*    
* 项目名称：present-android      
* 类描述： 学生签到完成的结果fragment
* 创建人：Larry-sea   
* 创建时间：2017/5/13 14:24   
* 修改人：Larry-sea  
* 修改时间：2017/5/13 14:24   
* 修改备注：   
* @version    
*    
*/
public class StudentSignResultDialogFragment extends DialogFragment {


    @BindView(R.id.tv_student_sign_result_name)
    TextView tvStudentSignResultName;
    @BindView(R.id.tv_student_sign_result_time)
    TextView tvStudentSignResultTime;
    @BindView(R.id.iv_student_sign_result_type)
    ImageView ivStudentSignResultType;
    Unbinder unbinder;

    //发起签到的类型
    String signType;

    //签到课程名
    String courseName;

    //签到时间
    String signDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_complete_sign, container, false);
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
        signType = getActivity().getIntent().getStringExtra("signType");
        courseName = getActivity().getIntent().getStringExtra("courseName");
        signDate = getActivity().getIntent().getStringExtra("signDate");
        tvStudentSignResultName.setText(signType);
        tvStudentSignResultTime.setText(signDate);
        Glide.with(getActivity()).load(initSignTypeDrawable("signType")).into(ivStudentSignResultType);

    }


    /**
     * 初始化签到类型的drawable
     *
     * @param signType
     * @return
     */
    public int initSignTypeDrawable(String signType) {
        int drawableId = 0;
        switch (signType) {
            case Constants.WIFI_SIGN:
                drawableId = R.drawable.ic_wifi_sign_type;
                break;
            case Constants.QR_CODE_SIGN:
                drawableId = R.drawable.ic_qr_sign_type;
                break;
            case Constants.NFC_CODE_SIGN:
                drawableId = R.drawable.ic_nfc_sign_type;
                break;
        }
        return drawableId;
    }

}