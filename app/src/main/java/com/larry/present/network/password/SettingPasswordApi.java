package com.larry.present.network.password;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Laiyin on 2017/8/20.
 */

public class SettingPasswordApi {


    private static Retrofit mRetrofit;


    public SettingPasswordApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 设置密码接口
     *
     * @param observer 观察者
     * @param phone 用户电话
     * @param password 加密的密码密文
     * @return
     */
    public Subscription settingPassword(Observer<String> observer, String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        jsonObject.put("password", password);
        return RxjavaUtil.subscribe(mRetrofit.create(IchangePassword.class)
                .settingPassword(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }

}
