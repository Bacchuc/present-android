package com.larry.present.network.student;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.bean.student.Student;
import com.larry.present.loginregister.dto.StudentLoginSuccessDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/*
*    
* 项目名称：present-android      
* 类描述： 学生信息的相关api
* 创建人：Larry-sea   
* 创建时间：2017/4/20 15:47   
* 修改人：Larry-sea  
* 修改时间：2017/4/20 15:47   
* 修改备注：   
* @version    
*    
*/
public class StudentApi {

    private static Retrofit mRetrofit;

    public StudentApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 提交学生信息
     */
    public void submitStudentInfo(Observer<String> observer, Student student) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("student", student);
        RxjavaUtil.subscribe(mRetrofit.create(Istudent.class)
                .submitStudentInfo(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 学生上传头像
     *
     * @param observer
     * @param phone    手机号
     * @param portrait 头像
     * @return
     */
    public Subscription studentUploadPortrait(Observer<String> observer, String phone, File portrait) {
        RequestBody headBody = RequestBody.create(MediaType.parse("multipart/form-data"), portrait);
        MultipartBody.Part portraitPart = MultipartBody.Part.createFormData("file", portrait.getName(), headBody);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), phone);
        return RxjavaUtil.subscribe(mRetrofit.create(Istudent.class)
                .studentUploadPortrait(portraitPart, requestBody)
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 获取学生个人信息
     *
     * @param observer
     * @param studentId
     * @return
     */
    public Subscription getStudentInfo(Observer<StudentLoginSuccessDto> observer, String studentId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentId", studentId);
        return RxjavaUtil.subscribe(mRetrofit.create(Istudent.class)
                .getStudentInfo(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<StudentLoginSuccessDto>()), observer);

    }
}
