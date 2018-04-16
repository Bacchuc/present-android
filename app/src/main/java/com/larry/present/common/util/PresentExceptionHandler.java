package com.larry.present.common.util;

import android.content.Context;
import android.widget.Toast;
import android.content.Intent;
import com.larry.present.exception.ApiException;
import com.larry.present.loginregister.activity.LoginActivity;

/*
*    
* 项目名称：present-android      
* 类描述： 异常处理类
* 创建人：Larry-sea   
* 创建时间：2017/5/6 19:47   
* 修改人：Larry-sea  
* 修改时间：2017/5/6 19:47   
* 修改备注：   
* @version    
*    
*/
public class PresentExceptionHandler extends ProcessException {



    @Override
    public void processException(Context context, Throwable e) {
        //如果是调用api自己的原因产生异常
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            Toast.makeText(context, apiException.getMessage(), Toast.LENGTH_SHORT).show();
            if(apiException.getMessage().equals("token已经过期，请重新登陆")){
                Intent intent=new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        }
        //捕捉到的一些系统级别的异常
        else {
            //这种级别错误直接反馈后台
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }


    }


}
