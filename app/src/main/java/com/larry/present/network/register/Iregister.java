package com.larry.present.network.register;

import com.larry.present.network.base.BaseCallModeal;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/4/19 7:33   
* 修改人：Larry-sea  
* 修改时间：2017/4/19 7:33   
* 修改备注：   
* @version    
*    
*/
public interface Iregister {

    /**
     * 注册接口         主要是验证登录账号是否已经注册
     *
     * @param phone 手机号
     * @return 返回用户的注册之后的唯一id
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("registerVerification")
    Observable<BaseCallModeal<String>> register(@Body RequestBody  phone);


}
