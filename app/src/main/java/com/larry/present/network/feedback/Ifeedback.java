package com.larry.present.network.feedback;

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
* 创建时间：2017/5/26 10:22   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 10:22   
* 修改备注：   
* @version    
*    
*/
public interface Ifeedback {

    /**
     * 提交反馈信息接口
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("submitFeedback")
    Observable<BaseCallModeal<String>> submitFeedback(@Body RequestBody body);


}
