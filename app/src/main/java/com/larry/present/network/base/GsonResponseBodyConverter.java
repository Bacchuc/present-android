package com.larry.present.network.base;

import android.util.Log;

import com.google.gson.Gson;
import com.larry.present.exception.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by Charles on 2016/3/17.
 * <p>
 * gson解析转换器
 */
class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;
    ApiException apiException;

    final static String TAG = GsonResponseBodyConverter.class.toString();

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) {
        String response = null;
        try {
            response = value.string();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        Log.d("Network", "response>>" + response);
        //httpResult 只解析result字段
        BaseCallModeal<T> httpResult = gson.fromJson(response, BaseCallModeal.class);
       /* if(User.getInstance().getSessionId()==null)
        {
            User.getInstance().setUserId(httpResult.getUser_id());
            User.getInstance().setSessionId(httpResult.getSession_id());
            saveUserIdAndSessionId(httpResult.getUser_id(), httpResult.getSession_id());

        }*/
        return gson.fromJson(response, type);
    }


    /**
     * TODO 保存用户id和sessionId
     * 用来存储用户id和sessionId
     *
     * @param userId
     * @param sessionId
     */
    public void saveUserIdAndSessionId(String userId, String sessionId) {
       /* //保存老师的sessionAndUserId
        SharedPreferenceUtil.getInstance().putStringValue();
        //学生的
        if (userId != null && sessionId != null) {
            SessionAndUserId sessionAndUserId = new SessionAndUserId(userId, sessionId);
            Gson gson = new Gson();
            String tempString = gson.toJson(sessionAndUserId, SessionAndUserId.class);
            SharedPreferenceUtil.getInstance().putStringValue(App.getApp(), Constants.SESSION_FILE_NAME, Constants.USERID_SESSIONID, tempString);
        } else {
            return;
        }*/

    }
}
