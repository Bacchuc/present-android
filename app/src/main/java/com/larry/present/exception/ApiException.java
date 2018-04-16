package com.larry.present.exception;

import static com.larry.present.config.ExceptionConfig.PASSWORD_WRONG;
import static com.larry.present.config.ExceptionConfig.SIGN_INFOR_DOSNT_EXIST;
import static com.larry.present.config.ExceptionConfig.TOKEN_IS_EXPIRED;
import static com.larry.present.config.ExceptionConfig.USER_ALREADY_EXIST;
import static com.larry.present.config.ExceptionConfig.USER_NOT_EXIST;
import static com.larry.present.config.ExceptionConfig.WRONG_PASSWORD;

/**
 * Created by larrysea on 16/3/10.
 * <p>
 * 当api接口返回出现异常时使用目的是为了统一处理异常信息
 */
public class ApiException extends RuntimeException {


    //老师用户已经注册
    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case USER_ALREADY_EXIST:
                message = "用户已经存在";
                break;
            case PASSWORD_WRONG:
                message = "密码错误";
                break;
            case SIGN_INFOR_DOSNT_EXIST:
                message = "考勤信息不存在";
                break;
            case TOKEN_IS_EXPIRED:
                message = "token已经过期，请重新登陆";
                break;

        }
        return message;
    }
}

