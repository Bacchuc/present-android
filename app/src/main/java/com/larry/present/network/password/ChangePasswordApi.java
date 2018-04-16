package com.larry.present.network.password;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/*
*    
* 项目名称：present-android      
* 类描述：   修改密码接口
* 创建人：Larry-sea   
* 创建时间：2017/5/26 10:49   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 10:49   
* 修改备注：   
* @version    
*    
*/
public class ChangePasswordApi {

    private static Retrofit mRetrofit;


    public ChangePasswordApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 修改密码接口
     *
     * @param observer 观察者
     * @param userType 用户类型是老师还是学生
     * @param userId   用户id
     * @param password 加密的密码密文
     * @return
     */
    public Subscription changePassword(Observer<String> observer, String userType, String userId, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userType", userType);
        jsonObject.put("userId", userId);
        jsonObject.put("password", password);
        return RxjavaUtil.subscribe(mRetrofit.create(IchangePassword.class)
                .resetPassword(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }

    /**
     * 忘记密码接口
     *
     * @param observer 观察者
     * @param phone 用户电话
     * @param password 修改的新密码
     * @return
     */
    public Subscription forgetPassword(Observer<String> observer, String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        jsonObject.put("password", password);
        return RxjavaUtil.subscribe(mRetrofit.create(IchangePassword.class)
                .forgetPassword(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }
}

