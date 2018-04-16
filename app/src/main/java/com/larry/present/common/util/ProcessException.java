package com.larry.present.common.util;

import android.content.Context;

/*
*    
* 项目名称：present-android      
* 类描述： 处理异常的抽象基类
* 创建人：Larry-sea   
* 创建时间：2017/5/6 19:45   
* 修改人：Larry-sea  
* 修改时间：2017/5/6 19:45   
* 修改备注：   
* @version    
*    
*/
public abstract class ProcessException {

    abstract public void processException(Context context,Throwable e);
}
