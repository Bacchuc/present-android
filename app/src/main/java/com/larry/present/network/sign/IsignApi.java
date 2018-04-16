package com.larry.present.network.sign;

import com.larry.present.bean.sign.CourseSign;
import com.larry.present.bean.sign.CourseSignInfoDto;
import com.larry.present.bean.sign.StudentCourseSignDto;
import com.larry.present.bean.sign.StudentSignInfoOfTermDto;
import com.larry.present.network.base.BaseCallModeal;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/*
*    
* 项目名称：present-android      
* 类描述：  签到相关的api
* 创建人：Larry-sea   
* 创建时间：2017/4/25 8:08   
* 修改人：Larry-sea  
* 修改时间：2017/4/25 8:08   
* 修改备注：   
* @version    
*    
*/
public interface IsignApi {
    /**
     * 学生签到接口
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("changeStudentSignState")
    Observable<BaseCallModeal<String>> studentSign(@Body RequestBody body);


    /**
     * 老师选择课程进行签到
     *
     * @param body
     * @return
     */

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("selectCourseToSign")
    Observable<BaseCallModeal<String>> selectCourseToSign(@Body RequestBody body);


    /**
     * 选择班级开始签到
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("selectClassToSign")
    Observable<BaseCallModeal<String>> selectClassToSign(@Body RequestBody body);

    /**
     * 获取某个课程的一个学期的记录
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getSignInfoOfCourseInTerm")
    Observable<BaseCallModeal<List<StudentSignInfoOfTermDto>>> getCourseSignInfoInTerm(@Body RequestBody body);


    /**
     * 返回某一次某个班级的所有学生的签到记录
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getSignInfoOfCourseInTerm")
    Observable<BaseCallModeal<List<StudentCourseSignDto>>> getCourseSignInfoOfOnce(@Body RequestBody body);


    /**
     * 学生获取某个课程的签到信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getCourseSignInfo")
    Observable<BaseCallModeal<List<CourseSignInfoDto>>> studentGetCourseSignInfoDto(@Body RequestBody body);


    /**
     * 判断学生是否参加了这个课程
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("isJoinTheCourse")
    Observable<BaseCallModeal<String>> isJoinCourse(@Body RequestBody body);


    /**
     * 获取某一次签到的所有学生信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getCourseSignInfoOnce")
    Observable<BaseCallModeal<List<StudentCourseSignDto>>> getCourseSignInfoOfOnceByCourseSignId(@Body RequestBody body);


    /**
     * 获取某一次签到的所有学生信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getAbsenceStudentList")
    Observable<BaseCallModeal<List<StudentCourseSignDto>>> getAbsenceStudentList(@Body RequestBody body);


    /**
     * 获取某个课程的所有考勤发起记录
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getCourseAllSignInfo")
    Observable<BaseCallModeal<List<CourseSign>>> getCourseAllSignInfo(@Body RequestBody body);


    /**
     * 发送某个课程下面的某个班级的这一学期的所有考勤记录
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("sendSignMailService")
    Observable<BaseCallModeal<String>> sendSignMail(@Body RequestBody body);


}



