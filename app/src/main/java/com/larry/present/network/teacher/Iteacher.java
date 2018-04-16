package com.larry.present.network.teacher;

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
* 类描述：  老师的一些相关接口
* 创建人：Larry-sea   
* 创建时间：2017/5/5 10:31   
* 修改人：Larry-sea  
* 修改时间：2017/5/5 10:31   
* 修改备注：   
* @version    
*    
*/
public interface Iteacher {

    /**
     * @param teacher 老师
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("submitTeacherInfo")
    Observable<BaseCallModeal<String>> submitStudentInfo(@Body RequestBody teacher);


    /**
     * 获取老师信息
     *
     * @param teacher 老师
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getTeacherInfo")
    Observable<BaseCallModeal<TeacherLoginSuccessDto>> getTeacherInfo(@Body RequestBody teacher);


}
