package com.larry.present.network.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.larry.present.common.context.AppContext;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Larry-sea on 2016/11/14.
 * <p>
 * <p>
 * retrofit  中使用缓存的客户端
 */

public class OkhttpClient {

    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }

    public static OkHttpClient getCacheOkHttpClient(final Context context) {
        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, "HttpResponseCache");
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);   //缓存可用大小为10M

        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
            Request request = chain.request();
            if (!isNetworkReachable(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);
            if (isNetworkReachable(context)) {
                int maxAge = 60;                  //在线缓存一分钟
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 4 * 7;     //离线缓存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };

        return new OkHttpClient.Builder()
                //设置网络缓存的拦截器
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                //设置日志拦截器
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder1 = request.newBuilder();

//                        AppContext.log(chain.request().url());
//                        if (chain.request().url().equals(TIT_LIB_URL + "studentLogin")
//                                || chain.request().url().equals(TIT_LIB_URL + "teacherLogin")
//                                || chain.request().url().equals(TIT_LIB_URL + "forgetPassword")
//                                || chain.request().url().equals(TIT_LIB_URL + "checkAppUpdateService")
//                                || chain.request().url().equals(TIT_LIB_URL + "registerVerification")
//                                || chain.request().url().equals(TIT_LIB_URL + "getAllSchool")
//                                || chain.request().url().equals(TIT_LIB_URL + "upload")
//                                || chain.request().url().equals(TIT_LIB_URL + "getClassId")
//                                || chain.request().url().equals(TIT_LIB_URL + "submitStudentInfo")
//                                || chain.request().url().equals(TIT_LIB_URL + "settingPassword")) {
//
//                            builder1.addHeader(null, null);
//                        } else {
//                            if (SharedPreferenceUtil.getInstance().getStringSharedValue(AppContext.getContext(), userInfoFileName, Constants.TOKEN) != null) {
//                                AppContext.log("-----------传Token----------");
//                                builder1.addHeader(Constants.TOKEN, SharedPreferenceUtil.getInstance().getStringSharedValue(AppContext.getContext(), userInfoFileName, Constants.TOKEN));
//                            } else {
//                                ToastUtil.showShort(AppContext.getContext(), "登陆已过期，请重新登陆");
//                                Intent intent = new Intent(AppContext.getContext(), LoginActivity.class);
//                                AppContext.getContext().startActivity(intent);
//                            }
//
//                        }

                        Request request1 = builder1.build();
                        for (Map.Entry<String, List<String>> entry : request1.headers().toMultimap().entrySet()) {
                            AppContext.log(entry.getKey() + "   K--V    " + entry.getValue().toString());
                        }
                        return chain.proceed(request1);
                    }
                })
                .cache(cache)
                .build();
    }
}