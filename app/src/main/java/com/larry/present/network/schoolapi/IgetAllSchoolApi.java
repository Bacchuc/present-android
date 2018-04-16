package com.larry.present.network.schoolapi;

import com.larry.present.bean.school.School;
import com.larry.present.network.base.BaseCallModeal;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;


/*
*    
* 项目名称：present      
* 类描述：  获取所全国所有学校的接口
* 创建人：Larry-sea   
* 创建时间：2017/4/9 11:29   
* 修改人：Larry-sea  
* 修改时间：2017/4/9 11:29   
* 修改备注：   
* @version    
*    
*/
public interface IgetAllSchoolApi {

    /**
     * @return 返回学校list
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getAllSchool")
    Observable<BaseCallModeal<List<School>>> getAllSchool(@Body RequestBody phone);

    /**
     * @return 返回学校list
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("getAllSchool")
    Call<BaseCallModeal<List<School>>> getAllSchoolOldWay(@Body RequestBody phone);

}
