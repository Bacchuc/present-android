package com.larry.present.network.base;

import com.alibaba.fastjson.JSON;

import okhttp3.RequestBody;

/*
*    
* 项目名称：present      
* 类描述： json的一些工具类
* 创建人：Larry-sea   
* 创建时间：2017/4/14 18:08   
* 修改人：Larry-sea  
* 修改时间：2017/4/14 18:08   
* 修改备注：   
* @version    
*    
*/
public class JsonUtil {


    /**
     * 将普通的请求对象转换成为RequestBody类型
     *
     * @param object
     * @return
     */
    public static <T> RequestBody convertObjectToRequestBody(T object) {
        if (object != null) {
            String jsonString = JSON.toJSONString(object);
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonString);
        } else {
            return null;
        }
    }


}
