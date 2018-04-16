package com.larry.present.network.classes;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.bean.classes.Classes;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/4/20 17:28   
* 修改人：Larry-sea  
* 修改时间：2017/4/20 17:28   
* 修改备注：   
* @version    
*    
*/
public class ClassApi {


    private static Retrofit mRetrofit;

    public ClassApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    //请求体
    RequestBody requestBody;

    /**
     * 根据班级名称,还有学校id查询班级id
     *
     * @param observer  回调观察者
     * @param className 班级名称
     * @param schoolId  学校id
     * @return
     */
    public Subscription queryClassId(Observer<String> observer, String className, String schoolId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("schoolId", schoolId);
        jsonObject.put("className", className);
        return RxjavaUtil.subscribe(mRetrofit.create(Iclasses.class)
                .getClassId(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);

    }

    /**
     * 添加班级信息
     *
     * @param observer  观察者
     * @param className 班级名称
     * @param schoolId  学校id
     * @return
     */
    public Subscription addClasses(Observer<Classes> observer, String className, String schoolId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("className", className);
        jsonObject.put("schoolId", schoolId);
        return RxjavaUtil.subscribe(mRetrofit.create(Iclasses.class)
                .addClasses(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<Classes>()), observer);
    }


    /**
     * 添加班级信息
     *
     * @param observer  观察者
     * @param teacherId 老师id
     * @param courseId  课程id
     * @return
     */
    public Subscription getClassesUnderCourse(Observer<List<Classes>> observer, String teacherId, String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        jsonObject.put("courseId", courseId);
        return RxjavaUtil.subscribe(mRetrofit.create(Iclasses.class)
                .getClassesUnderCourse(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<Classes>>()), observer);
    }

    /**
     * @param observer
     * @param className 班级名称
     * @param schoolId  学校id
     * @return
     */
    public Subscription getClassesInfo(Observer<Classes> observer, String className, String schoolId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("className", className);
        jsonObject.put("schoolId", schoolId);
        return RxjavaUtil.subscribe(mRetrofit.create(Iclasses.class)
                .getClassesInfo(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<Classes>()), observer);
    }

}
