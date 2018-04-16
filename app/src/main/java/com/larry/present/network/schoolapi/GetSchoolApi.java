package com.larry.present.network.schoolapi;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.bean.school.School;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;


/*
*    
* 项目名称：present      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/4/9 11:53   
* 修改人：Larry-sea  
* 修改时间：2017/4/9 11:53   
* 修改备注：   
* @version    
*    
*/
public class GetSchoolApi {

    private static Retrofit mRetrofit;

    public GetSchoolApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 获取全国所有大学的方法
     *
     * @param observer 订阅者
     * @param phone    用户注册手机号
     */
    public Subscription getAllSchool(Observer<List<School>> observer, String phone) {
        JSONObject phoneObject = new JSONObject();
        phoneObject.put("phone", phone);
        return RxjavaUtil.subscribe(mRetrofit.create(IgetAllSchoolApi.class)
                .getAllSchool(JsonUtil.convertObjectToRequestBody(phoneObject))
                .map(new ApiService.HttpResultFunc<List<School>>()), observer);
    }

}
