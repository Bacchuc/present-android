package com.larry.present.network.login;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.common.util.GetUniqueNameUtil;
import com.larry.present.loginregister.dto.StudentLoginSuccessDto;
import com.larry.present.loginregister.dto.TeacherLoginSuccessDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/*
*    
* 项目名称：present-android      
* 类描述： 登录接口
* 创建人：Larry-sea   
* 创建时间：2017/4/18 18:53   
* 修改人：Larry-sea  
* 修改时间：2017/4/18 18:53   
* 修改备注：   
* @version    
*    
*/
public class LoginApi {

    private static Retrofit mRetrofit;

    public LoginApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    /**
     * 学生登录方法
     *
     * @param observer 订阅者
     * @param password 用户注册手机号
     */
    public Subscription studentLogin(Observer<StudentLoginSuccessDto> observer, String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        jsonObject.put("password", password);
        jsonObject.put("imel", GetUniqueNameUtil.getUniqueName());
        return RxjavaUtil.subscribe(mRetrofit.create(Ilogin.class)
                .studentLogin(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<StudentLoginSuccessDto>()), observer);
    }


    /**
     * 老师登录方法
     *
     * @param observer 订阅者
     * @param password 用户注册手机号
     */
    public Subscription teacherLogin(Observer<TeacherLoginSuccessDto> observer, String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        jsonObject.put("password", password);
        return RxjavaUtil.subscribe(mRetrofit.create(Ilogin.class)
                .teacherLogin(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<TeacherLoginSuccessDto>()), observer);
    }

    /**
     * 通过imel查询用户id、时间
     * 然后检查id是否匹配，若匹配则放行，
     * 若不匹配则比较时间，若大于50分钟就正常登录和重新绑定账号和设备（即更改imel对应的用户id）
     * 小于则不放行并且提示距离上次登陆为满50分钟不可更改账号登陆
     * 匹配不成功直接走异常所以不需要dto
     *
     * @param observer
     * @param userId   用户登陆成功后返回的id
     * @param imel     设备唯一标识符
     * @return
     */
    public Subscription checkImelBind(Observer<String> observer, String userId, String imel) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("imel", imel);
        return RxjavaUtil.subscribe(mRetrofit.create(Ilogin.class)
                .checkImelBind(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }

}
