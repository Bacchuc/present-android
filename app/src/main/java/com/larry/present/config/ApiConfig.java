package com.larry.present.config;

/*  
*    
* 项目名称：present      
* 类描述： api接口的一些配置参数
* 创建人：Larry-sea   
* 创建时间：2017/4/9 10:37   
* 修改人：Larry-sea  
* 修改时间：2017/4/9 10:37   
* 修改备注：   
* @version    
*    
*/
public class ApiConfig {

    //9月21号修改
//    public static String TIT_LIB_URL = "http://192.168.43.47:8080/externalservice/";
//    public static String TIT_LIB_URL = "http://192.168.1.228:8080/externalservice/";
    public static String TIT_LIB_URL = "http://116.196.111.10:8032/present/externalservice/";

    //上传头像的服务器接口地址（因为这个上传头像使用的的是单独的servlet，所有不能使用统一的接口）
    public static String UPLOAD_PORTRAIT_PATH="http://192.168.1.102:8080/present/";

}
