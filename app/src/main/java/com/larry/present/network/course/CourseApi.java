package com.larry.present.network.course;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.bean.classes.Classes;
import com.larry.present.bean.course.Course;
import com.larry.present.bean.course.CourseAndTeacherDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/*
*    
* 项目名称：present-android      
* 类描述： 课程的移动端调用api
* 创建人：Larry-sea   
* 创建时间：2017/4/25 22:29   
* 修改人：Larry-sea  
* 修改时间：2017/4/25 22:29   
* 修改备注：   
* @version    
*    
*/
public class CourseApi {

    private static Retrofit mRetrofit;

    public CourseApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 老师获取所有课程
     *
     * @param observer  观察者
     * @param teacherId 老师id
     * @return
     */
    public Subscription teacherGetAllCourse(Observer<List<Course>> observer, String teacherId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        return RxjavaUtil.subscribe(mRetrofit.create(Icourse.class)
                .teacherGetAllCourse(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<Course>>()), observer);
    }


    /**
     * 学生获取所有课程
     *
     * @param observer  观察者
     * @param studentId 学生id
     * @return
     */
    public Subscription studentGetAllCourse(Observer<List<Course>> observer, String studentId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("classId", studentId);
        return RxjavaUtil.subscribe(mRetrofit.create(Icourse.class)
                .studentGetAllCourse(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<Course>>()), observer);
    }


    /**
     * 老师获取所有课程
     *
     * @param observer   观察者
     * @param teacherId  老师id
     * @param courseName 课程名
     * @return
     */
    public Subscription addCourse(Observer<Course> observer, String teacherId, String courseName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        jsonObject.put("courseName", courseName);
        return RxjavaUtil.subscribe(mRetrofit.create(Icourse.class)
                .addCourse(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<Course>()), observer);
    }


    /**
     * @param observer
     * @param courseId   课程id
     * @param classArray 班级数组
     * @return
     */
    public Subscription addClassesToCourse(Observer<String> observer, String courseId, List<Classes> classArray) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        jsonObject.put("classIdArray", classArray);
        return RxjavaUtil.subscribe(mRetrofit.create(Icourse.class)
                .addClassesToCourse(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 获取老师的和课程信息
     *
     * @param observer
     * @param courseId 课程id
     * @return
     */
    public Subscription getTeacherAndCourseInfo(Observer<CourseAndTeacherDto> observer, String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        return RxjavaUtil.subscribe(mRetrofit.create(Icourse.class)
                .getTeacherAndCourseInfo(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<CourseAndTeacherDto>()), observer);
    }




}
