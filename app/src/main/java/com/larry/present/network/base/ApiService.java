package com.larry.present.network.base;

import android.content.Context;
import android.widget.Toast;

import com.larry.present.bean.user.SessionAndUserId;
import com.larry.present.common.context.AppContext;
import com.larry.present.common.util.NetUtil;
import com.larry.present.exception.ApiException;

import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.functions.Func1;

import static com.larry.present.config.ApiConfig.TIT_LIB_URL;

/**
 * 项目名称：present
 * 类描述： 主要提供网络的一些配置，检查，和异常的相关处理，这个类是所有接口调用类的基础
 * 并且在提供userId和sessionId的存储刷新等作用
 * 创建人：Larry-sea
 * 创建时间：2017/4/8 20:44
 * 修改人：Larry-sea
 * 修改时间：2017/4/8 20:44
 * 修改备注：
 */
public class ApiService {

    private Retrofit mRetrofit;
    private SessionAndUserId sessionAndUserId;
    private static ApiService INSTANCE;             //本类的实例
    private final static String CHECK_NETWORK_AVAILABLE = "请检查网络是否可用";
    private Request build;

    public ApiService() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(OkhttpClient.getCacheOkHttpClient(AppContext.getContext()))
                    .baseUrl(TIT_LIB_URL)
                    .addConverterFactory(ResponseConvertFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        //todo 保存用户的sessionId 和userId
        //更新sessionID和userId
//       Gson gson = new Gson();
//        mSessionAndUserId = gson.fromJson(SharedPreferenceUtil.getInstance().getStringSharedValue(AppContext.getContext(), ApiConfig.SESSION_FILE_NAME, ApiConfig.USERID_SESSIONID), SessionAndUserId.class);
//        if (mSessionAndUserId != null) {
//            mSessionId = mSessionAndUserId.getSessionId();
//            mUserId = mSessionAndUserId.getUserId();
//        }
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static class HttpResultFunc<T> implements Func1<BaseCallModeal<T>, T> {
        @Override
        public T call(BaseCallModeal<T> httpResult) {
            if (httpResult.getCode() != 200) {
                throw new ApiException(httpResult.getCode());
            }
            return httpResult.getData();
        }

    }

    /**
     * 获取apiServeice实例
     *
     * @param context
     * @return
     */
    public static ApiService getInstance(final Context context) {
        checkNetWrokState(context);
        if (INSTANCE == null) {
            synchronized (ApiService.class) {
                if (INSTANCE == null)
                    INSTANCE = new ApiService();
            }
        }
        //TODO 同时获取用户的userId和sessionId
//        if (User.getInstance().getSessionId() != null) {
//            mSessionId = User.getInstance().getSessionId();
//            mUserId = User.getInstance().getUserId();
//        }
        return INSTANCE;
    }

    /**
     * 检查当前网络是否可用
     * <p>
     * 如果不可用则直接返回
     */
    public static void checkNetWrokState(Context context) {
        if (!NetUtil.isConnected(context)) {
            Toast.makeText(context, CHECK_NETWORK_AVAILABLE, Toast.LENGTH_SHORT).show();

        }
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public void setmRetrofit(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }
}
