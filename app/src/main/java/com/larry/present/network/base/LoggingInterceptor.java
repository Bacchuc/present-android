package com.larry.present.network.base;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/*
*    
* 项目名称：present      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/4/9 10:27   
* 修改人：Larry-sea  
* 修改时间：2017/4/9 10:27   
* 修改备注：   
* @version    
*    
*/
public class LoggingInterceptor implements Interceptor {


    final static String TAG = LoggingInterceptor.class.toString();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        long t1 = System.nanoTime();
        Log.i(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.i(TAG, String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        String responseBodyString = response.body().string();
        Log.i(TAG, "resoponse body:" + responseBodyString);
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseBodyString)).build();
    }
}
