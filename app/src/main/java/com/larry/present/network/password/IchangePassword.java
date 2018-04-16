package com.larry.present.network.password;

import com.larry.present.network.base.BaseCallModeal;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/*
*    
* 项目名称：present-android      
* 类描述：  修改密码接口
* 创建人：Larry-sea   
* 创建时间：2017/5/26 10:46   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 10:46   
* 修改备注：   
* @version    
*    
*/
public interface IchangePassword {


    /**
     * 修改密码接口
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("resetPassword")
    Observable<BaseCallModeal<String>> resetPassword(@Body RequestBody body);

    /**
     * 忘记密码接口
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("forgetPassword")
    Observable<BaseCallModeal<String>> forgetPassword(@Body RequestBody body);

    /**
     * 设置密码接口
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("settingPassword")
    Observable<BaseCallModeal<String>> settingPassword(@Body RequestBody body);

}
