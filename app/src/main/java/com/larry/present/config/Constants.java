package com.larry.present.config;

import android.os.Environment;

/*
*    
* 项目名称：present      
* 类描述： 配置的一些常量字符串
* 创建人：Larry-sea   
* 创建时间：2017/4/13 22:20   
* 修改人：Larry-sea  
* 修改时间：2017/4/13 22:20   
* 修改备注：   
* @version    
*    
*/
public class Constants {

    //学校id
    public final static String SCHOOL_ID = "school_id";

    //学校名称
    public final static String SCHOOLE_NAME = "schoole_name";

    //用户类型
    public final static String USER_TYPE = "USER_TYPE";

    //学生用户类型
    public final static int STUDENT_TYPE = 0X1;

    //老师用户类型
    public final static int TEACHER_TYPE = 0X2;

    //用户id
    public final static String USER_ID = "id";

    //用户密码
    public final static String USER_PASSWORD = "user_password";

    //学生头像
    public final static String PORTRAITPATH = "portrait_path";

    //token
    public final static String TOKEN = "token";

    //手机号
    public final static String PHONE = "phone";

    //签到的wifi信号id长度是21  位 2位首部标示MD  + 3位mac地址，和十六位的课程签到id
    public final static int WIFI_SIGN_BSSID_LENGTH = 21;

    //学生签到
    public final static String STUDENT_SIGN = "1";

    //学生病假
    public final static String STUDENT_SICK_LEAVE = "2";

    //班级名称
    public final static String CLASS_NAME = "className";

    //wifi签到
    public final static String WIFI_SIGN = "w";

    //二维码签到
    public final static String QR_CODE_SIGN = "q";

    //nfc签到
    public final static String NFC_CODE_SIGN = "n";

    //wifi 密码
    public final static String WIFI_PASSWORD = "god_war_peace_love";

    //学生缺勤
    public final static String STUDENT_ABSENCE = "3";

    //apk下载路径
    public static String downloadApkUrl = "http://192.168.1.3:8080/resource/apk/mukewang.apk";

    //指定apk文件夹路径
    public static String filePath = Environment.getExternalStorageDirectory() + "/com.tit.present/";

    //指定apk文件存放的路径
    public static String filePathApk = Environment.getExternalStorageDirectory() + "/com.tit.present/apk/";

    //指定apk文件存放的路径
    public static String filePathApkName = "present.apk";

    //存放用户信息文件的路径
//    public static String tokenFileAddress=Environment.getExternalStorageDirectory()+"/com.tit.present/user/";
//    public static String tokenFileAddress = Environment.getExternalStorageDirectory()
//            +File.separator
//            +"com.tit.present"
//            +File.separator
//            +"user"
//            +File.separator;

    //SP存放用户信息文件的文件名
    public static String userInfoFileName = "user";









}
