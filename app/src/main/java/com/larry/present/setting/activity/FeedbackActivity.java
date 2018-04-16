package com.larry.present.setting.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.CheckETEmptyUtil;
import com.larry.present.common.util.CommonUtil;
import com.larry.present.config.Constants;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.feedback.FeedbackApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述： 反馈activity
* 创建人：Larry-sea   
* 创建时间：2017/5/26 15:38   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 15:38   
* 修改备注：   
* @version    
*    
*/
public class FeedbackActivity extends AppCompatActivity {

    FeedbackApi feedbackApi;

    SubscriberOnNextListener<String> subscriberOnNextListener;
    @BindView(R.id.feedback_content)
    EditText etFeedbackContent;


    CheckETEmptyUtil checkETEmptyUtil;
    @BindView(R.id.feedback_toolbar)
    Toolbar feedbackToolbar;

    @OnClick(R.id.feedback_send_button)
    public void submit(View view) {
        feedbackApi = new FeedbackApi(ApiService.getInstance(this).getmRetrofit());
        String userId = null;
        String contactWay = null;
        if (AccountManager.returnLoginUserType() == Constants.TEACHER_TYPE) {
            userId = AccountManager.getTeacher().getId();
            contactWay = AccountManager.getTeacher().getMail();
        } else if (AccountManager.returnLoginUserType() == Constants.STUDENT_TYPE) {
            userId = AccountManager.getStudent().getId();
            contactWay = AccountManager.getStudent().getMail();
        }
        checkETEmptyUtil = new CheckETEmptyUtil(this);
        boolean isEmpty = checkETEmptyUtil.addView(etFeedbackContent).addTip(R.string.feed_back_content_cant_empty).isEmpty();
        if (!isEmpty) {
            String feedbackContent = etFeedbackContent.getText().toString().trim();
            feedbackApi.submitFeedback(new ProgressSubscriber<String>(subscriberOnNextListener, this),
                    userId, contactWay, feedbackContent, Build.VERSION.SDK, CommonUtil.getPhoneName());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initToolbar();
        initListener();
    }

    public void initListener() {
        subscriberOnNextListener = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(FeedbackActivity.this, R.string.thanks_your_feedback, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {

            }
        };
    }


    public void initToolbar() {
        feedbackToolbar.setTitle(R.string.feedback);
        feedbackToolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        feedbackToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(feedbackToolbar);
//        feedbackToolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

    }

}
