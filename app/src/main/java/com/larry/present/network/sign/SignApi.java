package com.larry.present.network.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.larry.present.bean.sign.CourseSign;
import com.larry.present.bean.sign.CourseSignInfoDto;
import com.larry.present.bean.sign.StudentCourseSignDto;
import com.larry.present.bean.sign.StudentSignInfoOfTermDto;
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
* 类描述： 签到api的接口调用方法
* 创建人：Larry-sea   
* 创建时间：2017/4/25 11:42   
* 修改人：Larry-sea  
* 修改时间：2017/4/25 11:42   
* 修改备注：   
* @version    
*    
*/
public class SignApi {

    private static Retrofit mRetrofit;

    public SignApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 学生开始签到
     *
     * @param observer     订阅者
     * @param courseSignId 所有发现的课程签到id
     * @param studentId    学生id
     * @param date         日期
     * @return
     */

    public Subscription studentSign(Observer<String> observer, List<String> courseSignId, String studentId, String date) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSignIdList", courseSignId);
        jsonObject.put("studentId", studentId);
        jsonObject.put("signTime", date);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .studentSign(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 选择课程开始签到
     *
     * @param teacherId   老师id
     * @param courseId    课程id
     * @param signType    签到类型
     * @param validOfTime 有效时间
     * @return
     */

    public Subscription selectCourseToSign(Observer<String> observer, String teacherId, String courseId, String signType, int validOfTime) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        jsonObject.put("courseId", courseId);
        jsonObject.put("signStartType", signType);
        jsonObject.put("validOfTime", validOfTime);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .selectCourseToSign(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 选择班级进行签到
     *
     * @param observer     事件监听器
     * @param courseSignId 课程签到id
     * @param classArray   班级数组
     * @return
     */

    public Subscription selectClassToSign(Observer<String> observer, String courseSignId, JSONArray classArray) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSignId", courseSignId);
        jsonObject.put("classArray", classArray);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .selectClassToSign(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 获取某个课程的一个学期的记录
     *
     * @param observer
     * @param teacherId 老师id
     * @param courseId  课程id
     * @param classId   班级id
     * @return
     */

    public Subscription getCourseSignInfoInTerm(Observer<List<StudentSignInfoOfTermDto>> observer, String teacherId, String courseId, String classId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        jsonObject.put("courseId", courseId);
        jsonObject.put("classId", classId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .getCourseSignInfoInTerm(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<StudentSignInfoOfTermDto>>()), observer);
    }


    /**
     * 返回某一次某个班级的所有学生的签到记录
     *
     * @param observer     观察者
     * @param courseSignId 课程签到id
     * @param classId      班级id
     * @return
     */

    public Subscription getCourseSignInfoOfOnce(Observer<List<StudentCourseSignDto>> observer, String courseSignId, String classId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSignId", courseSignId);
        jsonObject.put("classId", classId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .getCourseSignInfoOfOnce(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<StudentCourseSignDto>>()), observer);

    }


    /**
     * 学生获取某门课程的每次签到信息
     *
     * @param courseId  课程id
     * @param studentId 学生id
     * @return
     */

    public Subscription studentGetCourseSignInfoDto(Observer<List<CourseSignInfoDto>> observer, String courseId, String studentId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        jsonObject.put("studentId", studentId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .studentGetCourseSignInfoDto(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<CourseSignInfoDto>>()), observer);

    }


    /**
     * 判断学生是否加入了这门课程
     *
     * @param observer 回调观察者
     * @param courseId 课程id
     * @param classId  班级id
     * @return
     */
    public Subscription isJoinTheCourse(Observer<String> observer, String courseId, String classId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        jsonObject.put("classId", classId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .isJoinCourse(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);

    }


    /**
     * 获取某一次的课程签到状况,是获取所有学生的
     *
     * @param observer
     * @param courseSignId
     * @return
     */
    public Subscription getCourseSignInfoOnceByCourseSignId(Observer<List<StudentCourseSignDto>> observer, String courseSignId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSignId", courseSignId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .getCourseSignInfoOfOnceByCourseSignId(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<List<StudentCourseSignDto>>()), observer);

    }


    /**
     * 获取某次签到课程中缺勤的人清单
     *
     * @param observer
     * @param courseSignId
     * @return
     */
    public Subscription getAbsenceStudentInfo(Observer<List<StudentCourseSignDto>> observer, String courseSignId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSignId", courseSignId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .getAbsenceStudentList(JsonUtil.convertObjectToRequestBody(jsonObject)).map(new ApiService.HttpResultFunc<List<StudentCourseSignDto>>()), observer);

    }


    /**
     * 获取某个课程的所有发起的签到记录
     *
     * @param observer
     * @param courseId
     * @return
     */
    public Subscription getCourseAllSignInfo(Observer<List<CourseSign>> observer, String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .getCourseAllSignInfo(JsonUtil.convertObjectToRequestBody(jsonObject)).map(new ApiService.HttpResultFunc<List<CourseSign>>()), observer);
    }


    /**
     * 老师修改学生签到状态
     *
     * @param observer
     * @param courseSignId 课程签到id
     * @param studentId    学生id
     * @param signType     学生签到状态，具体的学生签到状态，有三种状态，签到，缺勤，病假
     * @return
     */
    public Subscription changeStudentSign(Observer<String> observer, String courseSignId, String studentId, String signType) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSignId", courseSignId);
        jsonObject.put("studentId", studentId);
        jsonObject.put("signType", signType);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .studentSign(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);
    }


    /**
     * 发送某个课程的某个班级的这学期的所有考勤信息邮件
     *
     * @param observer    观察者回调
     * @param teacherId   老师id
     * @param courseId    课程id
     * @param classId     班级id
     * @param mailAddress 老师的邮件地址
     * @return
     */
    public Subscription sendSignMail(final Observer<String> observer, final String teacherId, final String courseId, final String classId, final String mailAddress) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", teacherId);
        jsonObject.put("courseId", courseId);
        jsonObject.put("classId", classId);
        jsonObject.put("mailAddress", mailAddress);
        return RxjavaUtil.subscribe(mRetrofit.create(IsignApi.class)
                .sendSignMail(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<String>()), observer);

    }


}
