package com.larry.present.network.logo;

import com.alibaba.fastjson.JSONObject;
import com.larry.present.logo.dto.IsNeedToUpdateDto;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.base.JsonUtil;
import com.larry.present.network.base.RxjavaUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Laiyin on 2017/8/3.
 */

public class UpdateApi {

    private static Retrofit mRetrofit;


    public UpdateApi(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }


    /**
     * 更新系统接口 检查系统是否需要更新
     * @param observer
     * @param versionName 版本号
     * @return
     */
    public Subscription isNeedToUpdate(Observer<IsNeedToUpdateDto> observer, String versionName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("versionNumber", versionName);
        return RxjavaUtil.subscribe(mRetrofit.create(Iupdate.class)
                .isNeedToUpdate(JsonUtil.convertObjectToRequestBody(jsonObject))
                .map(new ApiService.HttpResultFunc<IsNeedToUpdateDto>()), observer);
    }

}
