package com.larry.present.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.R;
import com.larry.present.bean.school.School;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.BaseCallModeal;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.schoolapi.GetSchoolApi;
import com.larry.present.network.schoolapi.IgetAllSchoolApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/5/6 21:35   
* 修改人：Larry-sea  
* 修改时间：2017/5/6 21:35   
* 修改备注：   
* @version    
*    
*/
public class TestRxjavaActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;

    GetSchoolApi getSchoolApi;

    Subscription subscription;

    @OnClick(R.id.button)
    void onClick(View view) {
        // getSchoolApi.getAllSchool(subscriber, "fasdfasdfas");
     subscription = getSchoolApi.getAllSchool(subscriber, "fasdfasdfs");


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_rxjava_activity);
        ButterKnife.bind(this);
        getSchoolApi = new GetSchoolApi(ApiService.getInstance(TestRxjavaActivity.this).getmRetrofit());


    }

    private void getAllSchool() {
        IgetAllSchoolApi getSchoolApi = ApiService.getInstance(this).getmRetrofit().create(IgetAllSchoolApi.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", "fasfasdfa");
        Call<BaseCallModeal<List<School>>> call = getSchoolApi.getAllSchoolOldWay(JsonUtil.convertObjectToRequestBody(jsonObject));
        call.enqueue(new Callback<BaseCallModeal<List<School>>>() {
            @Override
            public void onResponse(Call<BaseCallModeal<List<School>>> call, Response<BaseCallModeal<List<School>>> response) {
                Log.e("TestRxjavaActivity", "完成");

            }

            @Override
            public void onFailure(Call<BaseCallModeal<List<School>>> call, Throwable t) {
                Log.e("TestRxjavaActivity", t.getMessage());

            }
        });
    }

    Subscriber<List<School>> subscriber = new Subscriber<List<School>>() {
        @Override
        public void onCompleted() {
            Log.e("TestRxjavaActivity", "完成");
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
            Log.e("faskldfjalksdk", e.getMessage());
        }

        @Override
        public void onNext(List<School> s) {
            Log.e("TestRxjavaActivity", s.get(0).toString());
        }
    };

    Observer<List<School>> observer = new Observer<List<School>>() {
        @Override
        public void onCompleted() {
            Log.e("TestRxjavaActivity", "完成");

        }

        @Override
        public void onError(Throwable e) {
            Log.e("faskldfjalksdk", e.getMessage());

        }

        @Override
        public void onNext(List<School> schools) {
            Log.e("TestRxjavaActivity", schools.get(0).toString());

        }
    };
}
