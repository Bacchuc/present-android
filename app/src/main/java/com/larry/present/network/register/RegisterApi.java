package com.larry.present.network.register;

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
* 类描述： 注册公用的api
* 创建人：Larry-sea   
* 创建时间：2017/4/19 7:36   
* 修改人：Larry-sea  
* 修改时间：2017/4/19 7:36   
* 修改备注：   
* @version    
*    
*/
public class RegisterApi {

    private static Retrofit mRetrofit;

    public RegisterApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 用户注册验证接口，验证手机号是否已经注册
     *
     * @param observer 订阅者
     * @param phone    用户注册手机号
     */
    public Subscription registerVerfication(Observer<String> observer, String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        return RxjavaUtil.subscribe(mRetrofit.create(Iregister.class)
                .register(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


}
