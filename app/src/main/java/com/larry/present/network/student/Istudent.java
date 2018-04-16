package com.larry.present.network.student;

import com.larry.present.loginregister.dto.StudentLoginSuccessDto;
import com.larry.present.network.base.BaseCallModeal;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/*
*    
* 项目名称：present-android      
* 类描述：  学生的接口
* 创建人：Larry-sea   
* 创建时间：2017/4/20 15:19   
* 修改人：Larry-sea  
* 修改时间：2017/4/20 15:19   
* 修改备注：   
* @version    
*    
*/
public interface Istudent {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("submitStudentInfo")
    Observable<BaseCallModeal<String>> submitStudentInfo(@Body RequestBody student);


    //todo 接口签名需要更改，具体代码看ApiService 和ChangePortraitImpl 这两个的实现

    /**
     * 学生上传头像
     *
     * @param portrait  头像
     * @param studentId 学生id
     * @return
     */
    @Multipart
    @POST("upload")
    Observable<BaseCallModeal<String>> studentUploadPortrait(@Part MultipartBody.Part portrait, @Part("studentId") RequestBody studentId);

    /**
     * 获取学生信息
     *
     * @param student
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getStudentInfo")
    Observable<BaseCallModeal<StudentLoginSuccessDto>> getStudentInfo(@Body RequestBody student);




}
