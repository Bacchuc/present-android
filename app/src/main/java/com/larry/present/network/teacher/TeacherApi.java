package com.larry.present.network.teacher;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.bean.teacher.Teacher;
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
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/5/5 10:38   
* 修改人：Larry-sea  
* 修改时间：2017/5/5 10:38   
* 修改备注：   
* @version    
*    
*/
public class TeacherApi {
    private static Retrofit mRetrofit;

    public TeacherApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 提交学生信息
     *
     * @param observer 观察者
     * @param teacher  老师
     */
    public Subscription submitTeacherInfo(Observer<String> observer, Teacher teacher) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacher", teacher);
        return RxjavaUtil.subscribe(mRetrofit.create(Iteacher.class)
                .submitStudentInfo(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }

    /**
     * 获取老师信息
     *
     * @param observer  观察者
     * @param teacherId 老师id
     * @return
     */
    public Subscription getTeacherInfo(Observer<TeacherLoginSuccessDto> observer, String teacherId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        return RxjavaUtil.subscribe(mRetrofit.create(Iteacher.class)
                .getTeacherInfo(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<TeacherLoginSuccessDto>()), observer);
    }


}
