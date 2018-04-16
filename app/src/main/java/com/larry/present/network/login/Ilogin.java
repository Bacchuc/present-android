package com.larry.present.network.login;

import com.larry.present.loginregister.dto.StudentLoginSuccessDto;
import com.larry.present.loginregister.dto.TeacherLoginSuccessDto;
import com.larry.present.network.base.BaseCallModeal;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/*
*    
* 项目名称：present-android      
* 类描述： 登录接口   使用范围老师登录，学生登录都是这个接口
* 创建人：Larry-sea   
* 创建时间：2017/4/18 18:44   
* 修改人：Larry-sea  
* 修改时间：2017/4/18 18:44   
* 修改备注：   
* @version    
*    
*/
public interface Ilogin {

    /**
     * 学生通过手机号密码登录
     *
     * @param studentLoginBody
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("studentLogin")
    Observable<BaseCallModeal<StudentLoginSuccessDto>> studentLogin(@Body RequestBody studentLoginBody);

    /**
     * 老师通过手机号和密码登录
     *
     * @param teacherLoginBody
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("teacherLogin")
    Observable<BaseCallModeal<TeacherLoginSuccessDto>> teacherLogin(@Body RequestBody teacherLoginBody);

    /**
     * 通过imel查询用户id、时间
     *
     * @param checkImelBindBody
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("checkImelBind")
    Observable<BaseCallModeal<String>> checkImelBind(@Body RequestBody checkImelBindBody);


}
