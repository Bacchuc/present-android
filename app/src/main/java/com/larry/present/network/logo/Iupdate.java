package com.larry.present.network.logo;

import com.larry.present.logo.dto.IsNeedToUpdateDto;
import com.larry.present.network.base.BaseCallModeal;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Laiyin on 2017/8/3.
 *
 * 系统更新接口
 */

public interface Iupdate {

    /**
     * 更新系统接口 检查系统是否需要更新
     *
     * @param updateBody
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("checkAppUpdateService")
    Observable<BaseCallModeal<IsNeedToUpdateDto>> isNeedToUpdate(@Body RequestBody updateBody);

}
