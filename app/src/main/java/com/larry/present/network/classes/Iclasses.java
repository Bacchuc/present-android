package com.larry.present.network.classes;

import com.larry.present.bean.classes.Classes;
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
* 类描述： 班级的接口
* 创建人：Larry-sea   
* 创建时间：2017/4/20 16:53   
* 修改人：Larry-sea  
* 修改时间：2017/4/20 16:53   
* 修改备注：   
* @version    
*    
*/
public interface Iclasses {

    /**
     * 通过班级名称，和学校id获取班级id
     *
     * @param requestBody 包含className,和schoolId
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getClassId")
    Observable<BaseCallModeal<String>> getClassId(@Body RequestBody requestBody);

    /**
     * 添加班级
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("addClasses")
    Observable<BaseCallModeal<Classes>> addClasses(@Body RequestBody body);


    /**
     * 老师获取某个班级下面的所有课程
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getClassesUnderCourse")
    Observable<BaseCallModeal<List<Classes>>> getClassesUnderCourse(@Body RequestBody body);


    /**
     * 获取班级信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getClassInfo")
    Observable<BaseCallModeal<Classes>> getClassesInfo(@Body RequestBody body);


}
