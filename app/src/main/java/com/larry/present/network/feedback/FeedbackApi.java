package com.larry.present.network.feedback;

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
* 类描述： 反馈信息接口
* 创建人：Larry-sea   
* 创建时间：2017/5/26 10:34   
* 修改人：Larry-sea  
* 修改时间：2017/5/26 10:34   
* 修改备注：   
* @version    
*    
*/
public class FeedbackApi {
    private static Retrofit mRetrofit;

    public FeedbackApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 提交反馈接口
     *
     * @param observer   观察者
     * @param userId     用户id
     * @param contactWay 联系方式
     * @param content    内容
     * @param osInfo     操作系统信息
     * @param deviceInfo 设备信息
     * @return
     */
    public Subscription submitFeedback(Observer<String> observer, String userId, String contactWay, String content, String osInfo, String deviceInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("contactWay", contactWay);
        jsonObject.put("content", content);
        jsonObject.put("osInfo", osInfo);
        jsonObject.put("deviceInfo", deviceInfo);
        return RxjavaUtil.subscribe(mRetrofit.create(Ifeedback.class)
                .submitFeedback(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


}
